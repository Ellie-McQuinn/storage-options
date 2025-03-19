package com.duck.elliemcquinn.template.task

import com.google.gson.JsonObject
import org.gradle.api.file.ArchiveOperations
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.MapProperty
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.mapProperty
import javax.inject.Inject

open class ProcessJsonTask @Inject constructor(@Internal val archiveOperations: ArchiveOperations) : Jar() {
    @InputFile
    val input: RegularFileProperty = objectFactory.fileProperty()

    @Input
    val filePatterns: ListProperty<String> = propertyFactory.listProperty(String::class.java)
        .convention(listOf("**/*.json", "**/*.mcmeta"))

    @Input
    val processors: MapProperty<String, JsonObject.() -> Unit> = objectFactory.mapProperty()

    override fun copy() {
        input.finalizeValue()
        filePatterns.finalizeValue()
        processors.finalizeValue()

        val processors = processors.get()

        from(archiveOperations.zipTree(input.get()))

        filesMatching(filePatterns.get()) {
            filter(
                mapOf("processor" to processors[name]),
                JsonProcessingReader::class.java
            )
        }

        super.copy()
    }
}
