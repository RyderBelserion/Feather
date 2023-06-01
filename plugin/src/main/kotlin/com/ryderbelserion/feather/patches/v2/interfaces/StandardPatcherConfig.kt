package com.ryderbelserion.feather.patches.v2.interfaces

import org.gradle.api.provider.Property

interface StandardPatcherConfig {

    // The api directory i.e flint-api
    val apiDirectory: Property<String>

    // The server directory i.e flint-server
    val serverDirectory: Property<String>

    /**
     * Sets the base name of the module plus sub names
     *
     * @param name base name of every module
     * @param api name of the api module
     * @param server name of the server module
     */
    fun baseName(name: String, api: String, server: String) {
        apiDirectory.set("$name-$api")
        serverDirectory.set("$name-$server")
    }
}