package com.example.tiktokvideo_downloader.domain.model.tiktok


import androidx.annotation.Nullable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TikTok(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("msg")
    val msg: String? = null,
    @SerialName("processed_time")
    val processedTime: Double? = null
)