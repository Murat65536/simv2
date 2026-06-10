# Running the WALA strip (local first, GCP as fallback)

Produces `src/main/generated/movement-stripped.jar` — the minimal, faithful
movement core. The analysis is a **one-time** build; only the OUTPUT matters.

## The pipeline is now two-phase — try locally first

The old single-phase run built a vanilla 0-1-CFA call graph over the FULL
universe (~31k MC classes + the JDK); its points-to fixpoint blew past ~164 GB
and never converged even on a 170 GB box. The pipeline now is:

1. **Phase A** — a cheap reachability pre-pass (0-CFA by default) from
   `ClientPlayerEntity.tickMovement()` over the full jar. Minutes, modest RAM.
2. **Scope pruning** — Phase B only sees the classes reachable in Phase A
   (expanded to the hierarchy/field/signature closure). This is
   output-preserving: coarse reachability is a superset of precise
   reachability, so nothing the 0-1-CFA run would use is removed.
3. **Phase B** — the precise 0-1-CFA call graph + backward slice over the
   pruned scope, with allocation-site instance keys for everything except
   strings and throwables (`SMUSH_STRINGS | SMUSH_THROWABLES` — types that
   cannot carry physics dataflow; `Vec3d`/`BlockPos` keep full allocation-site
   precision).

Run it locally before paying for a VM:

```powershell
.\gradlew :analysis:runWala -PanalysisXmx=12g
```

Knobs (all optional):

- `-PanalysisPhaseA=zerocfa|cha` — which cheap call graph drives the pruning.
  `zerocfa` (default) gives the tightest closure; `cha` skips points-to
  entirely (cheapest, coarser closure) if 0-CFA itself struggles.
- `-PanalysisSkipClinit=true` — escape hatch: skip static-initializer modeling
  in Phase B. MC's registry `<clinit>`s (Blocks, Items, ...) are a points-to
  bomb, but without them registry-object dispatch (e.g. friction via
  `block.getVelocityMultiplier()`) may lose its targets and fall out of the
  slice. Validate the output if you enable this.
- `-PanalysisMaxCgNodes=N` — fail fast if the call graph exceeds N nodes
  instead of grinding into swap (0 = unlimited).
- `-PanalysisXmx=...` — analysis JVM heap. `ExitOnOutOfMemoryError` is on, so
  an undersized heap dies clean with a clear message; bump and retry.

## Config that must be true before you spend any compute

- **Phase B call graph: 0-1-CFA** with `ALLOCATIONS | SMUSH_STRINGS |
  SMUSH_THROWABLES` instance keys — most precise where it matters, smallest
  output. NOT `SMUSH_PRIMITIVE_HOLDERS` (it would merge all `Vec3d`/`BlockPos`
  instances) and NOT `SMUSH_MANY`.
- **Slice: heap-on** (`DataDependenceOptions.NO_BASE_PTRS`) — captures the velocity
  physics (gravity / jump / setVelocity / knockback flow velocity→pos via the heap).
- **Exclusions intact** (heap + scope) — they bound both analysis cost and output.
- **Seed:** `Entity.pos` writes.

Verify after extracting:
```bash
grep -n NO_BASE_PTRS analysis/src/main/java/murat/simv2/analysis/WalaSlicer.java
grep -n SMUSH_STRINGS analysis/src/main/java/murat/simv2/analysis/WalaPipelineRunner.java
```

## If you still need a bigger box (GCP)

Only if the pruned Phase B run OOMs locally even after trying
`-PanalysisSkipClinit=true`. A 64–128 GB machine should be far more than
enough now; the 512 GB sizing below is the old worst case, kept for reference.

### 0. Before you provision
- **Quota:** large instances may exceed a project's default regional quota.
  Raise **N2 CPUs** (and check the region's memory quota) for the target region
  first — approval can take minutes to hours, so do it ahead of time.
- **On-demand, NOT Spot.** The run can't checkpoint; a Spot preemption at minute 50
  loses everything.
- (Optional) a GCS bucket for unattended output collection.

### 1. Provision — Ubuntu 24.04 (has openjdk-21)
```bash
gcloud compute instances create wala-strip \
  --zone=us-central1-a \
  --machine-type=n2-highmem-16 \
  --image-family=ubuntu-2404-lts-amd64 --image-project=ubuntu-os-cloud \
  --boot-disk-size=50GB --boot-disk-type=pd-ssd
```
`n2-highmem-16` = 16 vCPU / 128 GB. The points-to fixpoint is mostly
single-threaded, so the vCPUs mainly feed parallel GC — you're paying for RAM,
not cores.

### 2. Ship the code (no GitHub auth needed)
**Commit your changes first**, then export a clean tree of the committed state:
```bash
git archive --format=tar.gz -o /tmp/simv2.tgz HEAD
gcloud compute scp /tmp/simv2.tgz wala-strip:~/ --zone=us-central1-a
```

### 3. Ship the Minecraft merged jar
The Yarn-mapped merged jar — the same one the local run used. Only this jar is needed
(the analysis scope is the merged jar + the JDK); do NOT rebuild Loom on the VM.
```bash
gcloud compute scp \
  "C:\Users\Murat\Desktop\programming\java\simv2\.gradle\loom-cache\minecraftMaven\net\minecraft\minecraft-merged-736b6061eb\1.21.5-net.fabricmc.yarn.1_21_5.1.21.5+build.1-v2\minecraft-merged-736b6061eb-1.21.5-net.fabricmc.yarn.1_21_5.1.21.5+build.1-v2.jar" \
  wala-strip:~/mc/ --zone=us-central1-a
```

### 4. On the VM: bootstrap + run
```bash
gcloud compute ssh wala-strip --zone=us-central1-a

sudo apt-get update && sudo apt-get install -y openjdk-21-jdk tmux
mkdir -p simv2 && tar xzf simv2.tgz -C simv2 && cd simv2
chmod +x gradlew
tmux new -s wala            # so an SSH drop doesn't kill the run
```
Attended run:
```bash
sh ./gradlew :analysis:runWala --no-daemon --console=plain \
  -PmcJar="$HOME/mc/minecraft-merged-736b6061eb-1.21.5-net.fabricmc.yarn.1_21_5.1.21.5+build.1-v2.jar" \
  -PanalysisXmx=110g \
  2>&1 | tee ~/wala-gcp-run.log
```
- `-Xmx110g` leaves headroom for OS / metaspace / GC on a 128 GB box.
- `ExitOnOutOfMemoryError` is on, so an undersized heap dies clean with a clear
  message. If it OOMs, try `-PanalysisSkipClinit=true` before going bigger.

#### Unattended variant (auto-collect + auto-stop — bounds cost if you walk away)
```bash
set -o pipefail
sh ./gradlew :analysis:runWala --no-daemon --console=plain \
  -PmcJar="$HOME/mc/minecraft-merged-736b6061eb-1.21.5-net.fabricmc.yarn.1_21_5.1.21.5+build.1-v2.jar" \
  -PanalysisXmx=110g 2>&1 | tee ~/wala-gcp-run.log
STATUS=$?
gsutil cp ~/wala-gcp-run.log gs://YOUR_BUCKET/wala-out/                       # always
[ $STATUS -eq 0 ] && gsutil cp \
  src/main/generated/movement-stripped.jar \
  src/main/generated/movement-slice.json gs://YOUR_BUCKET/wala-out/           # on success
sudo poweroff                                                                  # always
```

### 5. Confirm success (read the log)
- `--- Phase A: ZEROCFA reachability pre-pass ---` then
  `Phase A closure: keeping K / N jar classes` — K should be a small fraction of N.
- `Building 0-1-CFA call graph (ALLOCATIONS | SMUSH_STRINGS | SMUSH_THROWABLES)`.
- `Call graph: N nodes`, then `Slice: ... -> C classes, M methods`.
- Class count is a focused movement core.

### 6. Collect + tear down
Attended: `gcloud compute scp wala-strip:~/simv2/src/main/generated/movement-stripped.jar . --zone=us-central1-a`

Then **delete** the instance (a stopped instance still bills its disk):
```bash
gcloud compute instances delete wala-strip --zone=us-central1-a
```
