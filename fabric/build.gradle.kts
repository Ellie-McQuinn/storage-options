plugins {
    id("template-fabric")
}

repositories {
    maven {
        name = "Terraformers"
        url = uri("https://maven.terraformersmc.com/")
    }
}

dependencies {
    modRuntimeOnly("com.terraformersmc:modmenu:${"11.0.3"}")
}
