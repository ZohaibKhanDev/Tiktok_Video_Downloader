package com.example.tiktokvideo_downloader.domain.model.tiktok


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("ai_dynamic_cover")
    val aiDynamicCover: String,
    @SerialName("anchors")
    val anchors: String?,
    @SerialName("anchors_extras")
    val anchorsExtras: String,
    @SerialName("author")
    val author: Author,
    @SerialName("aweme_id")
    val awemeId: String,
    @SerialName("collect_count")
    val collectCount: Int,
    @SerialName("comment_count")
    val commentCount: Int,
    @SerialName("commerce_info")
    val commerceInfo: CommerceInfo,
    @SerialName("commercial_video_info")
    val commercialVideoInfo: String,
    @SerialName("cover")
    val cover: String,
    @SerialName("create_time")
    val createTime: Int,
    @SerialName("digg_count")
    val diggCount: Int,
    @SerialName("download_count")
    val downloadCount: Int,
    @SerialName("duration")
    val duration: Int,
    @SerialName("id")
    val id: String,
    @SerialName("is_ad")
    val isAd: Boolean,
    @SerialName("item_comment_settings")
    val itemCommentSettings: Int,
    @SerialName("music")
    val music: String,
    @SerialName("music_info")
    val musicInfo: MusicInfo,
    @SerialName("origin_cover")
    val originCover: String,
    @SerialName("play")
    val play: String,
    @SerialName("play_count")
    val playCount: Int,
    @SerialName("region")
    val region: String,
    @SerialName("share_count")
    val shareCount: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("title")
    val title: String,
    @SerialName("wm_size")
    val wmSize: Int,
    @SerialName("wmplay")
    val wmplay: String
)