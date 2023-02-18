package com.elyasasmad.my.prayertimes.data.remote

import com.elyasasmad.my.prayertimes.data.remote.dto.PostRequest
import com.elyasasmad.my.prayertimes.data.remote.dto.PostResponse
import com.elyasasmad.my.prayertimes.data.remote.dto.PrayerTimeResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsService {
    suspend fun getPosts(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?
    suspend fun getPrayerTimes(): PrayerTimeResponse?

    companion object {
        fun create(): PostsService {
            return PostsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}