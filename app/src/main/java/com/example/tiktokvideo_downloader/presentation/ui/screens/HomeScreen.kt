package com.example.tiktokvideo_downloader.presentation.ui.screens

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tiktokvideo_downloader.R
import com.example.tiktokvideo_downloader.domain.model.tiktok.Data
import com.example.tiktokvideo_downloader.domain.model.tiktok.TikTok
import com.example.tiktokvideo_downloader.domain.usecase.ResultState
import com.example.tiktokvideo_downloader.presentation.viewmmodel.MainViewModel
import org.koin.compose.koinInject

@Composable
fun HomeScreen() {
    val viewModel: MainViewModel = koinInject()
    var isLoading by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    var url by remember {
        mutableStateOf("")
    }
    val state by viewModel.allDownloader.collectAsState()
    var tikTokData by remember {
        mutableStateOf<TikTok?>(null)
    }

    val verticalScroll = rememberScrollState()
    LaunchedEffect(state) {
        when (state) {
            is ResultState.Error -> {
                isLoading = false
                val error = (state as ResultState.Error).error
                println(error)
            }

            ResultState.Loading -> {

            }

            is ResultState.Success -> {
                isLoading = false
                val success = (state as ResultState.Success).success
                tikTokData = success
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScroll)
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.topbar),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(40.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider()


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(1200.dp)
                .background(Color(0XFFdf6d1e)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Tik Tok Downloader",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "TikTok Downloader, Download Video TikTok Without Watermark (TikTok MP3 & MP4)",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(start = 14.dp),
                color = Color.White
            )

            TextField(
                value = url,
                onValueChange = {
                    url = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color(0XFFf1dccd),
                    unfocusedContainerColor = Color(0XFFf1dccd)
                ),
                placeholder = {
                    Text(text = "Insert a link", fontSize = 13.sp, color = Color(0XFF757575))
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            isLoading = true
                            viewModel.getAllDownloader(url, 5)
                        }
                    )
                },
                singleLine = true
            )

            Button(
                onClick = {
                    isLoading = true
                    viewModel.getAllDownloader(url, 5)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .height(54.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF2172f6))
            ) {
                Text(
                    text = "DOWNLOAD",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                tikTokData?.data?.let {
                    VideoDownloader(data = it, context)
                }
            }
        }
    }
}


@Composable
fun VideoDownloader(data: Data, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0XFFdf6d1e)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        AsyncImage(
            model = data.originCover,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )


        Button(
            onClick = { downloadVideo(context, data.play, "video_without_watermark.mp4") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF2172f6))
        ) {
            Text(
                text = "Download Without WaterMark",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }


        Button(
            onClick = { downloadVideo(context, data.wmplay, "video_with_watermark.mp4") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF2172f6))
        ) {
            Text(
                text = "Download With WaterMark",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }


        Button(
            onClick = { downloadMP3(context, data.musicInfo.play, "download.mp3") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(54.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF2172f6))
        ) {
            Text(
                text = "Download mp3",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

fun downloadVideo(context: Context, url: String, fileName: String) {
    val request =
        DownloadManager.Request(Uri.parse(url)).setTitle(fileName).setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true).setAllowedOverRoaming(true)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)

    Toast.makeText(context, "Download Started", Toast.LENGTH_SHORT).show()
}

fun downloadMP3(context: Context, url: String, fileName: String) {
    val request = DownloadManager.Request(Uri.parse(url)).setTitle(fileName)
        .setDescription("Downloading MP3...")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setAllowedOverMetered(true).setAllowedOverRoaming(true)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC, fileName)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)

    Toast.makeText(context, "MP3 Download Started", Toast.LENGTH_SHORT).show()
}