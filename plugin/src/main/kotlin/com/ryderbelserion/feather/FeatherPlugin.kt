package com.ryderbelserion.feather

import com.ryderbelserion.feather.patches.tasks.RebuildTask
import com.ryderbelserion.feather.patches.tasks.PatchTask
import com.ryderbelserion.feather.utils.GitUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class FeatherPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        GitUtils().checkForGit()

        //val patcher = extensions.create("patcher", PatcherExtension::class.java)

        //val webhook = extensions.create("webhook", WebhookExtension::class.java)

        // The task that clones and sets up all our necessary folders for the first time.
        project.tasks.register<PatchTask>("applyPatches") {
            group = "feather"

            //extension = patcher
        }

        // The task that rebuilds our patches onto the source code.
        project.tasks.register<RebuildTask>("rebuildPatches") {
            group = "feather"

            //extension = patcher
        }

        //project.tasks.register<WebhookTask>("webhook") {
        //    group = "feather"

        //    extension = webhook
        //}
    }
}

fun main() {
    //val hash = shellRun("git", listOf("rev-parse", "--short", "origin/main"))
    //val end = shellRun("git", listOf("rev-parse", "--short", "HEAD@{21.days.ago}"))

    //println(Patcher().gitFormat(Patcher().gitHistory(end, hash), "ryder", "example", "\n"))
}
