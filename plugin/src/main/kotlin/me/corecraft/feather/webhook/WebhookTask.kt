package me.corecraft.feather.webhook

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import task.WebhookExtension

abstract class WebhookTask : DefaultTask() {

    @get:Input
    lateinit var extension: WebhookExtension

    /** Ktor client for easy requests. */
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    @TaskAction
    fun feather() {
        val feather = extension

        val url = feather.url()

        runBlocking(Dispatchers.IO) {
            val response = client.post(url) {
                headers {
                    append(HttpHeaders.ContentType, ContentType.Application.Json)
                }

                setBody(feather.build())
            }

            println("Webhook Status: ${response.status}")
        }
    }
}