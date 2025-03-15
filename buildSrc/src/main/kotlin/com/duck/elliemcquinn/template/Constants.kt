package com.duck.elliemcquinn.template

import org.gradle.jvm.toolchain.JavaLanguageVersion

object Constants {
    const val GROUP = "com.duck.elliemcquinn.storageoptions"
    const val MOD_ID = "ellsso"
    const val MOD_NAME = "Ellie's Storage Options"
    const val MOD_VERSION = "2101.0.0.1"

    val CONTRIBUTORS = linkedMapOf(
        "Ellie McQuinn" to "Project Owner"
    )

    val EXTRA_MOD_INFO_REPLACEMENTS = mapOf<String, String>(

    )

    val JAVA_VERSION = JavaLanguageVersion.of(21)
    const val JETBRAIN_ANNOTATIONS_VERSION = "24.1.0"

    const val MIXIN_VERSION = "0.8.5"
    const val MIXIN_EXTRAS_VERSION = "0.3.5"

    const val MINECRAFT_VERSION = "1.21.1"
    const val FL_MINECRAFT_CONSTRAINT = "1.21.1"
    const val NF_MINECRAFT_CONSTRAINT = "[1.21.1]"

    // https://parchmentmc.org/docs/getting-started#choose-a-version/
    const val PARCHMENT_MINECRAFT = "1.21"
    const val PARCHMENT_RELEASE = "2024.07.28"

    // https://fabricmc.net/develop/
    const val FABRIC_API_VERSION = "0.110.0+1.21.1"
    const val FABRIC_KOTLIN_VERSION = "1.13.0+kotlin.2.1.0"
    const val FABRIC_LOADER_VERSION = "0.16.10"

    const val NEOFORM_VERSION = "1.21.1-20240808.144430" // // https://projects.neoforged.net/neoforged/neoform/
    const val NEOFORGE_VERSION = "21.1.133" // https://projects.neoforged.net/neoforged/neoforge/
    const val NEOFORGE_KOTLIN_VERSION = "5.7.0"
    const val FML_CONSTRAINT = "[4,)" // https://projects.neoforged.net/neoforged/fancymodloader/
}
