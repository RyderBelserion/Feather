package com.ryderbelserion.feather.patches.v2.interfaces

import org.gradle.api.file.DirectoryProperty

interface MinimalPatcherConfig {

    // The patch directory i.e patches/api
    val apiDirectory: DirectoryProperty

    // The output directory i.e flint-api
    val apiOutDirectory: DirectoryProperty

    // The patch directory i.e patches/server
    val serverDirectory: DirectoryProperty

    // The output directory i.e flint-server
    val serverOutDirectory: DirectoryProperty

}