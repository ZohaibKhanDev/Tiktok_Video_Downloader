package com.example.tiktokvideo_downloader.data.remote

import com.example.tiktokvideo_downloader.domain.model.tiktok.TikTok
import com.example.tiktokvideo_downloader.utlis.Constant.API_HOST
import com.example.tiktokvideo_downloader.utlis.Constant.API_KEY
import com.example.tiktokvideo_downloader.utlis.Constant.TIMEOUT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object TikTokApiClient {
    @OptIn(ExperimentalSerializationApi::class)
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                })
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        }

        install(HttpTimeout) {
            socketTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
        }
    }

    suspend fun analyzeVideo(url: String, hd: Int = 0): TikTok {
        val response: TikTok = client.get("https://$API_HOST/analysis") {
            header("x-rapidapi-host", API_HOST)
            header("x-rapidapi-key", API_KEY)
            url {
                parameters.append("url", url)
                parameters.append("hd", hd.toString())
            }
        }.body()

        return response
    }
}