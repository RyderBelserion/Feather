package com.ryderbelserion.feather

import com.ryderbelserion.feather.git.Patcher
import com.ryderbelserion.feather.git.PatcherExtension
import com.ryderbelserion.feather.git.tasks.RebuildTask
import com.ryderbelserion.feather.git.tasks.PatchTask
import com.ryderbelserion.feather.webhook.WebhookTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import task.WebhookExtension

class FeatherPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        plugins.apply("java")

        Patcher().gitExists()

        val patcher = extensions.create("patcher", PatcherExtension::class.java)

        val webhook = extensions.create("webhook", WebhookExtension::class.java)

        // The task that clones and sets up all our necessary folders for the first time.
        project.tasks.register<PatchTask>("applyPatches") {
            group = "feather"

            extension = patcher
        }

        // The task that rebuilds our patches onto the source code.
        project.tasks.register<RebuildTask>("rebuildPatches") {
            group = "feather"

            extension = patcher
        }

        project.tasks.register<WebhookTask>("webhook") {
            group = "feather"

            extension = webhook
        }
    }
}