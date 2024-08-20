package com.example.tiktokvideo_downloader.domain.repository

import com.example.tiktokvideo_downloader.data.remote.TikTokApiClient
import com.example.tiktokvideo_downloader.data.repository.ApiClient
import com.example.tiktokvideo_downloader.domain.model.tiktok.TikTok

class Repository : ApiClient {
    override suspend fun analyzeVideo(url: String, hd: Int): TikTok {
        return TikTokApiClient.analyzeVideo(url, hd)
    }
}