import com.duck.elliemcquinn.template.toTitleCase
import com.duck.elliemcquinn.template.Constants
import com.duck.elliemcquinn.template.task.ProcessJsonTask

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
            ideName = "NeoForge ${name.toTitleCase()} (${project.path})"
        }

        create("client") { client() }
        create("data") {
            data()

            programArguments.addAll(
                "--mod", Constants.MOD_ID,
                "--output", file("src/generated/resources").absolutePath,
                "--existing", findProject(":common")!!.file("src/main/resources").absolutePath,
                "--all"
            )
        }
        create("server") { server() }
    }

    mods {
        create(Constants.MOD_ID) {
            sourceSet(sourceSets.main.get())
        }
    }
}

repositories {
    maven {
        name = "Kotlin For Forge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge")
    }
}

dependencies {
    implementation("thedarkcolour:kotlinforforge-neoforge:${Constants.NEOFORGE_KOTLIN_VERSION}")
}

sourceSets.main {
    resources.srcDirs("src/generated/resources")
}

tasks.jar {
    archiveClassifier = "fat"
}

tasks.processResources {
    exclude("*.accesswidener")
}

tasks.register("processJson", ProcessJsonTask::class) {
    group = "multiloader"
    dependsOn(tasks.jar)
    input.set(tasks.jar.get().outputs.files.singleFile)
    archiveClassifier = ""
}

tasks.build {
    dependsOn("processJson")
}
