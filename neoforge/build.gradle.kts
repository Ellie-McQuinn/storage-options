import org.gradle.kotlin.dsl.support.uppercaseFirstChar

plugins {
    id("net.neoforged.moddev") version "2.0.78"
}

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
