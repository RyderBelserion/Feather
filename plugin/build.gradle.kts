plugins {
    id("feather.root-plugin")

    `maven-publish`
}

tasks {
    shadowJar {
        archiveBaseName.set("${rootProject.name}-${rootProject.version}")

        archiveClassifier.set("")
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

publishing {
    repositories {
        maven("https://repo.crazycrew.us/api") {
            name = "crazycrew"
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = "${rootProject.name.lowercase()}-api"
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}