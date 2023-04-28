package com.ryderbelserion.feather.git

import com.lordcodes.turtle.shellRun
import kotlin.system.exitProcess

class Patcher {

    fun gitExists() {
        runCatching {
            shellRun("git", listOf("--version"))
        }.onFailure {
            println("[Feather] Git was not found! Exiting now...")
            exitProcess(1)
        }
    }
}
