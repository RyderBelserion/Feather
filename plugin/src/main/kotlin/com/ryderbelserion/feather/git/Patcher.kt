package com.ryderbelserion.feather.git

import com.lordcodes.turtle.shellRun
import kotlin.system.exitProcess

class Patcher {

    fun gitHistory(startHash: String, endHash: String, user: String, project: String, newLine: String): String {
        val commit = shellRun("git", listOf("log", "$startHash..$endHash", "--format=format:%h %s"))

        val hash = commit.take(7)
        val msg = commit.substring(8)

        return "[$hash](https://github.com/$user/$project/commit/$hash) $msg$newLine"
    }

    fun gitExists() {
        runCatching {
            shellRun("git", listOf("--version"))
        }.onFailure {
            println("[Feather] Git was not found! Exiting now...")
            exitProcess(1)
        }
    }
}