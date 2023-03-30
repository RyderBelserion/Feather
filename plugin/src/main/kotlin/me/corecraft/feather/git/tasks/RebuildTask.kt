package me.corecraft.feather.git.tasks

import me.corecraft.feather.files.FileUtil
import me.corecraft.feather.git.PatcherExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class RebuildTask : DefaultTask() {

    @get:Input
    lateinit var extension: PatcherExtension

    @TaskAction
    fun feather() {
        val feather = extension.build()

        val directory: File = if (feather.plugin.path != null) {
            feather.plugin.path.toFile()
        } else {
            project.rootDir
        }

        val workspace = File("${directory}/${feather.plugin.workspace}")

        val patchDir = File("${directory}/patches")
        if (!patchDir.exists()) patchDir.mkdirs()

        FileUtil.rebuildPatches(workspace, patchDir)
    }
}