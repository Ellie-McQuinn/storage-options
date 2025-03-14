plugins {
    idea
    `java-library`
}

// Find checksums here: https://gradle.org/release-checksums/
// Run gradlew :wrapper a couple of times to update.
tasks.wrapper {
    gradleVersion = "8.13"
    distributionSha256Sum = "20f1b1176237254a6fc204d8434196fa11a4cfb387567519c61556e8710aed78"
    distributionType = Wrapper.DistributionType.BIN
}


subprojects {
    apply<JavaLibraryPlugin>()

    version = "2101.0.0.1"
    group = "com.duck.elliemcquinn"

    base.archivesName = "ellies-storage-options"

    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    sourceSets.main {
        resources {
            srcDir("src/generated/resources")
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
