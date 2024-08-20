package com.example.tiktokvideo_downloader.domain.model.tiktok


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TikTok(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: Data,
    @SerialName("msg")
    val msg: String,
    @SerialName("processed_time")
    val processedTime: Double
)