import com.duck.elliemcquinn.template.Constants

plugins {
    id("template-child")
    id("net.neoforged.moddev")
}

neoForge {
    version = Constants.NEOFORGE_VERSION

    accessTransformers.from(project(":common").neoForge.accessTransformers.files)

    parchment {
        minecraftVersion = Constants.PARCHMENT_MINECRAFT
        mappingsVersion = Constants.PARCHMENT_RELEASE
    }

    runs {
        configureEach {
            systemProperty("neoforge.enabledGameTestNamespaces", Constants.MOD_ID)
        }

        create("client") {
            client()
            ideName = "NeoForge Client (:neoforge)"
        }

        create("commonData") {
            data()
            ideName = "Common Data (:neoforge)"

            val common = findProject(":common")!!

            programArguments.addAll(
                "--mod", Constants.MOD_ID,
                "--output", common.file("src/generated/resources").absolutePath,
                "--existing", common.file("src/main/resources").absolutePath,
                "--all"
            )

            systemProperty("ellsso.datagen.common", "true")
        }

        create("data") {
            data()
            ideName = "NeoForge Data (:neoforge)"

            programArguments.addAll(
                "--mod", Constants.MOD_ID,
                "--output", file("src/generated/resources").absolutePath,
                "--existing", findProject(":common")!!.file("src/main/resources").absolutePath,
                "--all"
            )
        }

        create("server") {
            server()
            ideName = "NeoForge Server (:neoforge)"
        }
    }

    mods {
        create(Constants.MOD_ID) {
            sourceSet(sourceSets.main.get())
        }
    }
}

dependencies {
    implementation("thedarkcolour:kotlinforforge-neoforge:${Constants.NEOFORGE_KOTLIN_VERSION}")
}

sourceSets.main {
    resources.srcDirs("src/generated/resources")
}

tasks.processResources {
    exclude("*.accesswidener")
}
