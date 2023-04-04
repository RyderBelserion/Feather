plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.gradle.kotlin.dsl)
    implementation(libs.gradle.plugin.publish)

    implementation(libs.shadow)

    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.content)
    implementation(libs.ktor.gson)

    implementation(libs.turtle)
}