plugins {
    id("feather.root-plugin")

    `maven-publish`
}

repositories {

}

dependencies {

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
            description = "A git wrapper to create patches much like Paperweight by PaperMC"
            implementationClass = "me.corecraft.feather.FeatherPlugin"
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