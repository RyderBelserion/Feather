plugins {
    id("com.github.johnrengelman.shadow")
    id("org.gradle.kotlin.kotlin-dsl")
    id("com.gradle.plugin-publish")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api(kotlin("stdlib"))

    implementation("com.lordcodes.turtle:turtle:0.8.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}