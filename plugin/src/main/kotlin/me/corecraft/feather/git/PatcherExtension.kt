package me.corecraft.feather.git

import java.nio.file.Path

abstract class PatcherExtension {

    private var datafolder: Path? = null
    private var workspace: String = ""
    private var sha: String = ""
    private var url: String = ""
    private var autoupdate: Boolean = false

    /**
     * Sets the root folder.
     */
    fun datafolder(datafolder: Path) {
        this.datafolder = datafolder
    }

    /**
     * Sets the directory where we clone the repository.
     */
    fun workspace(workspace: String) {
        this.workspace = workspace
    }

    /**
     * Sets the commit hash
     */
    fun sha(sha: String) {
        this.sha = sha
    }

    /**
     * Gets the commit hash
     */
    fun sha(): String {
        return this.sha
    }

    fun autoupdate(autoupdate: Boolean) {
        this.autoupdate = autoupdate
    }

    fun autoupdate(): Boolean {
        return this.autoupdate
    }

    /**
     * Sets the .git url
     */
    fun url(url: String) {
        this.url = url
    }

    /**
     * Gets the .git url
     */
    fun url(): String {
        return this.url
    }

    internal fun build(): Extension {
        return Extension(
            Plugin(this.datafolder, this.workspace),
            Upstream(this.sha, this.autoupdate, this.url)
        )
    }

    data class Extension(
        val plugin: Plugin,
        val upstream: Upstream,
    )

    data class Plugin(
        val path: Path?,
        val workspace: String
    )

    data class Upstream(
        val sha: String,
        val autoUpstream: Boolean,
        val url: String
    )
}