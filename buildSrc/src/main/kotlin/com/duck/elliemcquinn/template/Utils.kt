package com.duck.elliemcquinn.template

fun String.toTitleCase(): String {
    return this.split(" ").joinToString(" ") { it.replaceFirstChar(Char::titlecaseChar) }
}
