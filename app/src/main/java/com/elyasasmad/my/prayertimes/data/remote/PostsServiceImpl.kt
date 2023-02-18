package com.elyasasmad.my.prayertimes.data.remote

import com.elyasasmad.my.prayertimes.data.remote.dto.PostRequest
import com.elyasasmad.my.prayertimes.data.remote.dto.PostResponse
import com.elyasasmad.my.prayertimes.data.remote.dto.PrayerTimeResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostsServiceImpl(
    private val client: HttpClient
): PostsService {

    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get {
                url(HttpRoutes.POSTS)
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: java.lang.Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: java.lang.Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    override suspend fun getPrayerTimes(): PrayerTimeResponse? {
        return try {
            client.get {
                url(HttpRoutes.ESOLAT_URL)
            }
        } catch (e: RedirectResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("Error: ${e.response.status.description}")
            null
        } catch (e: java.lang.Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}