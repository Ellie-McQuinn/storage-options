import com.duck.elliemcquinn.template.Constants
import com.duck.elliemcquinn.template.task.JsonProcessingReader
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import kotlin.jvm.java

plugins {
    `java-library`
    kotlin("jvm")
}

group = Constants.GROUP
version = Constants.MOD_VERSION

base.archivesName = "${Constants.MOD_ID}-${project.name}-${Constants.MINECRAFT_VERSION}"

java.toolchain.languageVersion = Constants.JAVA_VERSION

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
        languageVersion = KotlinVersion.KOTLIN_2_1
    }
}

repositories {
    mavenCentral()

    exclusiveContent {
        forRepository {
            maven {
                name = "Sponge"
                url = uri("https://repo.spongepowered.org/repository/maven-public/")
            }
        }
        filter { includeGroupAndSubgroups("org.spongepowered") }
    }

    exclusiveContent {
        forRepositories(
            maven {
                name = "ParchmentMC"
                url = uri("https://maven.parchmentmc.org/")
            },
            maven {
                name = "NeoForge"
                url = uri("https://maven.neoforged.net/releases/")
            }
        )
        filter { includeGroup("org.parchmentmc.data") }
    }

    exclusiveContent {
        forRepository {
            maven {
                name = "Unofficial CurseForge Maven"
                url = uri("https://cursemaven.com/")
            }
        }
        filter {
            includeGroup("curse.maven")
        }
    }

    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth Maven"
                url = uri("https://api.modrinth.com/maven/")
            }
        }
        filter {
            includeGroup("maven.modrinth")
        }
    }

    exclusiveContent {
        forRepository {
            maven {
                name = "Kotlin for Forge Maven"
                url = uri("https://thedarkcolour.github.io/KotlinForForge/")
            }
        }
        filter {
            includeGroup("thedarkcolour")
        }
    }
}

dependencies {
    compileOnly(group = "org.jetbrains", name = "annotations", version = Constants.JETBRAIN_ANNOTATIONS_VERSION)
}

tasks.jar {
    manifest {
        attributes(mapOf(
            "Specification-Title" to Constants.MOD_NAME,
            "Specification-Vendor" to Constants.CONTRIBUTORS.firstEntry().key,
            "Specification-Version" to archiveVersion,
            "Implementation-Title" to project.name,
            "Implementation-Version" to archiveVersion,
            "Implementation-Vendor" to Constants.CONTRIBUTORS.firstEntry().key,
            "Built-On-Minecraft" to Constants.MINECRAFT_VERSION
        ))
    }

    exclude("**/datagen/**")
    exclude(".cache/**")

    rootDir.resolve("LICENSE").also { if (it.exists()) from(it) }
    rootDir.resolve("LICENSE.md").also { if (it.exists()) from(it) }
}

tasks.processResources {
    val replacements = mutableMapOf(
        "version" to version,

        "java_version" to Constants.JAVA_VERSION.asInt(),
        "minecraft_version" to Constants.MINECRAFT_VERSION,
        "fl_minecraft_constraint" to Constants.FL_MINECRAFT_CONSTRAINT,
        "nf_minecraft_constraint" to Constants.NF_MINECRAFT_CONSTRAINT,

        "fabric_loader_version" to Constants.FABRIC_LOADER_VERSION,
        "fabric_api_version" to Constants.FABRIC_API_VERSION,
        "fabric_kotlin_version" to Constants.FABRIC_KOTLIN_VERSION.substringBefore('+'),

        "fml_version_constraint" to Constants.FML_CONSTRAINT,
        "neoforge_version" to Constants.NEOFORGE_VERSION,
        "neoforge_kotlin_version" to Constants.NEOFORGE_KOTLIN_VERSION
    )
    replacements.putAll(Constants.EXTRA_MOD_INFO_REPLACEMENTS)

    inputs.properties(replacements)
    filesMatching(listOf("fabric.mod.json", "quilt.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json", "*.mcmeta")) {
        expand(replacements)
    }

    filesMatching(listOf("*.json", "*.mcmeta")) {
        filter(JsonProcessingReader::class.java)
    }
}
