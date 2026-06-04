# One-shot WALA strip on Google Cloud

Produces `src/main/generated/movement-stripped.jar` — the minimal, faithful
movement core — from a single high-RAM run. The analysis is a **one-time** build;
only the OUTPUT matters, so we run the most precise (smallest-output) config and
don't care what it costs in RAM/time.

## Config that must be true before you spend VM time
- **Call graph: 0-1-CFA** — the only builder (the 0-CFA option was removed). Most
  precise, smallest output. Peak seen ~164 GB+; this is why we need the big box.
- **Slice: heap-on** (`DataDependenceOptions.NO_BASE_PTRS`) — captures the velocity
  physics (gravity / jump / setVelocity / knockback flow velocity→pos via the heap).
- **Exclusions intact** (heap + scope) — they bound both analysis cost and output.
- **Seed:** `Entity.pos` writes.

Verify on the VM after extracting:
```bash
grep -n NO_BASE_PTRS analysis/src/main/java/murat/simv2/analysis/WalaSlicer.java
grep -n makeZeroOneCFABuilder analysis/src/main/java/murat/simv2/analysis/WalaPipelineRunner.java
```

## 0. Before you provision
- **Quota:** a 64-vCPU / 512 GB instance usually exceeds a project's default regional
  quota. Raise **N2 CPUs** (and check the region's memory quota) for the target region
  first — approval can take minutes to hours, so do it ahead of time.
- **On-demand, NOT Spot.** The run can't checkpoint; a Spot preemption at minute 50
  loses everything.
- (Optional) a GCS bucket for unattended output collection.

## 1. Provision — 512 GB, Ubuntu 24.04 (has openjdk-21)
```bash
gcloud compute instances create wala-strip \
  --zone=us-central1-a \
  --machine-type=n2-highmem-64 \
  --image-family=ubuntu-2404-lts-amd64 --image-project=ubuntu-os-cloud \
  --boot-disk-size=50GB --boot-disk-type=pd-ssd
```
`n2-highmem-64` = 64 vCPU / 512 GB. Bump to `n2-highmem-80` (640 GB) if you want margin
above the unknown true peak. The points-to fixpoint is mostly single-threaded, so the
vCPUs mainly feed parallel GC — you're paying for RAM, not cores.

## 2. Ship the code (no GitHub auth needed)
**Commit your changes first** so `HEAD` has the precise-CFA default, then export a clean
tree of the committed state:
```bash
git archive --format=tar.gz -o /tmp/simv2.tgz HEAD
gcloud compute scp /tmp/simv2.tgz wala-strip:~/ --zone=us-central1-a
```

## 3. Ship the Minecraft merged jar
The Yarn-mapped merged jar — the same one the local run used. Only this jar is needed
(the analysis scope is the merged jar + the JDK); do NOT rebuild Loom on the VM.
```bash
gcloud compute scp \
  "C:\Users\Murat\Desktop\programming\java\simv2\.gradle\loom-cache\minecraftMaven\net\minecraft\minecraft-merged-736b6061eb\1.21.5-net.fabricmc.yarn.1_21_5.1.21.5+build.1-v2\minecraft-merged-736b6061eb-1.21.5-net.fabricmc.yarn.1_21_5.1.21.5+build.1-v2.jar" \
  wala-strip:~/mc/ --zone=us-central1-a
```

## 4. On the VM: bootstrap + run
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
  -PanalysisXmx=460g \
  2>&1 | tee ~/wala-gcp-run.log
```
- The call graph is hard-wired to 0-1-CFA (precise) — there is no `-PanalysisCfa` flag.
- `-Xmx460g` leaves ~52 GB for OS / metaspace / GC on a 512 GB box.
- `ExitOnOutOfMemoryError` is on, so an undersized heap dies clean with a clear message.
  If it OOMs at 460g, the box isn't big enough — go to `m1-ultramem` (~1 TB) or trim the
  `WALA_EXCLUSIONS` scope; a retry at the same size won't help.

### Unattended variant (auto-collect + auto-stop — bounds cost if you walk away)
```bash
set -o pipefail
sh ./gradlew :analysis:runWala --no-daemon --console=plain \
  -PmcJar="$HOME/mc/minecraft-merged-736b6061eb-1.21.5-net.fabricmc.yarn.1_21_5.1.21.5+build.1-v2.jar" \
  -PanalysisXmx=460g 2>&1 | tee ~/wala-gcp-run.log
STATUS=$?
gsutil cp ~/wala-gcp-run.log gs://YOUR_BUCKET/wala-out/                       # always
[ $STATUS -eq 0 ] && gsutil cp \
  src/main/generated/movement-stripped.jar \
  src/main/generated/movement-slice.json gs://YOUR_BUCKET/wala-out/           # on success
sudo poweroff                                                                  # always
```

## 5. Confirm success (read the log)
- `Building 0-1-CFA call graph (precise ...)` — not 0-CFA.
- `Call graph: N nodes`, then `Slice: ... -> C classes, M methods`.
- `Verify: C classes, 0 with structural problems (BasicVerifier)`.
- Class count is a focused movement core (markedly fewer than a 0-CFA run would give).

## 6. Collect + tear down
Attended: `gcloud compute scp wala-strip:~/simv2/src/main/generated/movement-stripped.jar . --zone=us-central1-a`

Then **delete** the instance (a stopped instance still bills its disk):
```bash
gcloud compute instances delete wala-strip --zone=us-central1-a
```
