package com.example.tiktokvideo_downloader.data.repository

import com.example.tiktokvideo_downloader.domain.model.tiktok.TikTok

interface ApiClient {
    suspend fun analyzeVideo(url: String, hd: Int = 0): TikTok
}
