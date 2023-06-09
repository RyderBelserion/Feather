plugins {
    id("feather.root-plugin")

    `maven-publish`
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets.main.get().allSource)
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)

        archiveClassifier.set("")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = rootProject.group.toString()
                artifactId = "${rootProject.name.lowercase()}-api"
                version = rootProject.version.toString()

                from(javaComponent)
                artifact(sourcesJar)
            }
        }

        repositories {
            maven {
                credentials {
                    this.username = System.getenv("gradle_username")
                    this.password = System.getenv("gradle_password")
                }

                url = uri("https://repo.crazycrew.us/first-party/")
            }
        }
    }
}

gradlePlugin {
    plugins {
        create("feather") {
            id = "featherpatcher"
            displayName = "Feather"
            description = "A neat little gradle plugin with anything I need."
            implementationClass = "com.ryderbelserion.feather.FeatherPlugin"
            version = rootProject.version
        }
    }
}

val javaComponent: SoftwareComponent = components["java"]