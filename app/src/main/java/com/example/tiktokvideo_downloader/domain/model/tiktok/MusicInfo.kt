package com.example.tiktokvideo_downloader.domain.model.tiktok


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MusicInfo(
    @SerialName("album")
    val album: String,
    @SerialName("author")
    val author: String,
    @SerialName("cover")
    val cover: String,
    @SerialName("duration")
    val duration: Int,
    @SerialName("id")
    val id: String,
    @SerialName("original")
    val original: Boolean,
    @SerialName("play")
    val play: String,
    @SerialName("title")
    val title: String
)