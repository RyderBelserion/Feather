package com.ryderbelserion.feather.patches.v2.interfaces.upstream

import com.ryderbelserion.feather.patches.v2.interfaces.StandardPatcherConfig
import org.gradle.api.Action
import org.gradle.api.provider.Property

interface RepoPatcherUpstream {

    // The github url
    val url: Property<String>

    /**
     * Returns a completed GitHub url.
     *
     * @param owner github user/organization
     * @param repo github repository
     */
    fun github(owner: String, repo: String): String {
        return "https://github.com/$owner/$repo.git"
    }

    fun withPatcher(action: Action<StandardPatcherConfig>)
}