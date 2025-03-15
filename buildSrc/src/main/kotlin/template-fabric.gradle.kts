import com.duck.elliemcquinn.template.Constants
import com.duck.elliemcquinn.template.task.ProcessJsonTask

plugins {
    id("template-child")
    id("fabric-loom")
}

dependencies {
    minecraft("com.mojang:minecraft:${Constants.MINECRAFT_VERSION}")

    @Suppress("UnstableApiUsage")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${Constants.PARCHMENT_MINECRAFT}:${Constants.PARCHMENT_RELEASE}@zip")
    })

    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = Constants.FABRIC_LOADER_VERSION)
    modImplementation(group = "net.fabricmc.fabric-api", name = "fabric-api", version = Constants.FABRIC_API_VERSION)
    modImplementation(group = "net.fabricmc", name = "fabric-language-kotlin", version = Constants.FABRIC_KOTLIN_VERSION)
}

fabricApi {
    configureDataGeneration {
        modId = Constants.MOD_ID
        outputDirectory = file("src/generated/resources")
    }
}

loom {
    val accessWidener = project(":common").file("src/main/resources/${Constants.MOD_ID}.accesswidener")
    if (accessWidener.exists()) {
        accessWidenerPath = accessWidener
    }

    @Suppress("UnstableApiUsage")
    mixin {
        useLegacyMixinAp = false
    }

    runs {
        named("client") {
            client()

            configName = "Fabric Client"
            isIdeConfigGenerated = true
        }

        named("server") {
            server()

            configName = "Fabric Server"
            isIdeConfigGenerated = true
        }

        named("datagen") {
            configName = "Fabric Data"
            isIdeConfigGenerated = true
        }
    }
}


tasks.remapJar.configure {
    archiveClassifier = "fat"
}

tasks.processResources {
    exclude("META-INF/accesstransformer.cfg")
}

tasks.register("processJson", ProcessJsonTask::class) {
    group = "multiloader"
    dependsOn(tasks.remapJar)
    input.set(tasks.remapJar.get().outputs.files.singleFile)
    archiveClassifier = ""

    processors.put("quilt.mod.json") {
        val contributors = getAsJsonObject("quilt_loader").getAsJsonObject("metadata").getAsJsonObject("contributors")

        for ((contributor, role) in Constants.CONTRIBUTORS) {
            contributors.addProperty(contributor, role)
        }
    }
}

tasks.build {
    dependsOn("processJson")
}
