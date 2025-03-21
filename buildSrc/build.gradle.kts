import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()

    exclusiveContent {
        forRepository {
            maven {
                name = "FabricMC's Maven"
                url = uri("https://maven.fabricmc.net/")
            }
        }
        filter {
            includeGroup("net.fabricmc")
        }
    }

    exclusiveContent {
        forRepository {
            maven {
                name = "QuiltMC's Release Maven"
                url = uri("https://maven.quiltmc.org/repository/release/")
            }
        }
        filter {
            includeGroup("org.quiltmc")
            includeGroup("org.quiltmc.loom")
            includeGroup("org.quiltmc.parsers")
        }
    }
}

kotlin {
    compilerOptions {
        languageVersion = KotlinVersion.KOTLIN_2_0
        apiVersion = KotlinVersion.KOTLIN_2_0
    }
}

dependencies {
    implementation(group = "net.neoforged", name = "moddev-gradle", version = "2.0.78") // https://projects.neoforged.net/neoforged/moddevgradle/
    implementation(group = "net.fabricmc", name = "fabric-loom", version = "1.10.4") // https://maven.fabricmc.net/net/fabricmc/fabric-loom/
    implementation(group = "com.google.code.gson", name = "gson", version = "2.11.0")

    implementation(group = "org.jetbrains.kotlin", name = "kotlin-gradle-plugin", version = "2.0.21")
}
