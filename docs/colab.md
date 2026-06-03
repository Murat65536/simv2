# Running the movement analysis on Google Colab

The analysis code is Java, but Colab can still run it because a Colab notebook
cell can execute Linux shell commands. The reliable path is:

1. Start a **High-RAM** Colab runtime.
2. Install JDK 21 in the VM.
3. Clone this repository.
4. Let Fabric Loom populate the Minecraft merged-jar cache.
5. Run the `analysis` Gradle task without the local fixed heap/metaspace caps.
6. Download or copy the generated artifacts from `src/main/generated`.

GPU is not useful for the current WALA analysis. This workload is CPU and RAM
bound.

## Notebook

Use [`notebooks/simv2-analysis-colab.ipynb`](../notebooks/simv2-analysis-colab.ipynb)
as the main entry point. Upload it to Colab, then run the cells from top to
bottom.

The notebook defaults to:

- Repository: `https://github.com/Murat65536/simv2.git`
- Branch: `analysis`
- Output directory: `/content/simv2/src/main/generated`
- Gradle daemon heap: small, so Gradle itself does not consume the machine
- Analysis heap: no fixed `-Xmx`; the JVM uses most of the Colab VM via
  `-XX:MaxRAMPercentage`

If you have local changes that are not pushed, push them to a branch or upload a
zip of the repository to Colab before running the analysis.

## Manual Colab commands

These are the equivalent commands if you do not want to use the notebook.

```bash
apt-get update -qq
if apt-cache show openjdk-21-jdk-headless >/dev/null 2>&1; then
  apt-get install -y openjdk-21-jdk-headless
else
  apt-get install -y wget gpg ca-certificates
  install -d -m 0755 /etc/apt/keyrings
  wget -qO- https://packages.adoptium.net/artifactory/api/gpg/key/public \
    | gpg --dearmor > /etc/apt/keyrings/adoptium.gpg
  . /etc/os-release
  echo "deb [signed-by=/etc/apt/keyrings/adoptium.gpg] https://packages.adoptium.net/artifactory/deb ${VERSION_CODENAME} main" \
    > /etc/apt/sources.list.d/adoptium.list
  apt-get update -qq
  apt-get install -y temurin-21-jdk
fi
java -version

git clone --branch analysis --single-branch https://github.com/Murat65536/simv2.git
cd simv2

mkdir -p ~/.gradle
cat > ~/.gradle/gradle.properties <<'EOF'
org.gradle.jvmargs=-Xmx4G -Dfile.encoding=UTF-8
org.gradle.daemon=false
org.gradle.parallel=true
EOF

chmod +x ./gradlew
./gradlew --no-daemon :compileJava --stacktrace
./gradlew --no-daemon :analysis:runWala \
  -PanalysisXmx=unlimited \
  -PanalysisMaxMetaspace=unlimited \
  -PanalysisMaxRamPercentage=90 \
  --stacktrace
```

`-PanalysisXmx=unlimited` removes the fixed heap cap from the analysis JVM.
`-PanalysisMaxMetaspace=unlimited` removes the fixed class-metadata cap.
`-PanalysisMaxRamPercentage=90` tells Java that it may use most of the Colab
container's memory instead of Java's lower default ergonomics.

If Colab terminates the runtime instead of reporting a Java OOM, reduce
`analysisMaxRamPercentage` to `80` or `85`.

## If the merged Minecraft jar is not auto-detected

The `:analysis:runWala` task searches the Loom and Gradle caches for a
`minecraft-merged-*.jar`. Running `:compileJava` first usually creates the jar.
If auto-detection still fails, pass the jar explicitly:

```bash
./gradlew --no-daemon :analysis:runWala \
  -PanalysisXmx=unlimited \
  -PanalysisMaxMetaspace=unlimited \
  -PanalysisMaxRamPercentage=90 \
  -PmcJar=/content/path/to/minecraft-merged.jar \
  -PoutputDir=/content/simv2/src/main/generated \
  --stacktrace
```

The task writes:

- `movement-slice.json`
- `mirror-closure.json`
- `movement-fields.txt`
- `movement-stripped.jar`
