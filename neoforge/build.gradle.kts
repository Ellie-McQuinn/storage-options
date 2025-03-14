import org.gradle.kotlin.dsl.support.uppercaseFirstChar

plugins {
    `java-library`
    id("net.neoforged.moddev") version "2.0.78"
}

version = "2101.0.0.1"

neoForge {
    version = "21.1.133"

    parchment {
        mappingsVersion = "2024.11.17"
        minecraftVersion = "1.21.1"
    }

    runs {
        create("client") {
            client()
        }

        create("server") {
            server()
        }

        create("data") {
            data()

            programArguments.addAll(
                "--mod", "ellsso",
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath
            )
        }

        configureEach {
            ideName = "NeoForge " + name.uppercaseFirstChar()
            logLevel = org.slf4j.event.Level.INFO
        }
    }

    mods {
        create("ellsso") {
            sourceSet(sourceSets.main.get())
        }
    }
}

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