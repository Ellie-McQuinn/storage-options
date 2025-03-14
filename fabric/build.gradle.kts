plugins {
    id("fabric-loom") version "1.10-SNAPSHOT"
    id("org.jetbrains.kotlin.jvm") version "2.1.10"
}

repositories {
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/")
    }
}

dependencies {
    minecraft("net.minecraft:minecraft:${"1.21.1"}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${"0.16.10"}")

    modImplementation("net.fabricmc.fabric-api:fabric-api:${"0.115.1+1.21.1"}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${"1.13.1+kotlin.2.1.10"}")

    modImplementation("com.terraformersmc:modmenu:${"11.0.3"}")
}