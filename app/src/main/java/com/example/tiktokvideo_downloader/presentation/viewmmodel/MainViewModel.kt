package com.example.tiktokvideo_downloader.presentation.viewmmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tiktokvideo_downloader.domain.model.tiktok.TikTok
import com.example.tiktokvideo_downloader.domain.repository.Repository
import com.example.tiktokvideo_downloader.domain.usecase.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _allDownloader = MutableStateFlow<ResultState<TikTok>>(ResultState.Loading)
    val allDownloader: StateFlow<ResultState<TikTok>> = _allDownloader.asStateFlow()

    fun getAllDownloader(url: String, hd: Int) {
        viewModelScope.launch {
            _allDownloader.value = ResultState.Loading
            try {
                val response = repository.analyzeVideo(url, hd)
                _allDownloader.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allDownloader.value = ResultState.Error(e)
            }
        }
    }
}