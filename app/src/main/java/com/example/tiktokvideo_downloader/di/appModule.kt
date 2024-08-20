package com.example.tiktokvideo_downloader.di

import com.example.tiktokvideo_downloader.domain.repository.Repository
import com.example.tiktokvideo_downloader.presentation.viewmmodel.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single { Repository() }
    single { MainViewModel(get()) }
}