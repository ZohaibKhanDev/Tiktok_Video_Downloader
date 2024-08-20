package com.example.tiktokvideo_downloader.domain.model.tiktok


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Author(
    @SerialName("avatar")
    val avatar: String,
    @SerialName("id")
    val id: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("unique_id")
    val uniqueId: String
)