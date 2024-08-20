package com.example.tiktokvideo_downloader.domain.model.tiktok


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommerceInfo(
    @SerialName("adv_promotable")
    val advPromotable: Boolean,
    @SerialName("auction_ad_invited")
    val auctionAdInvited: Boolean,
    @SerialName("branded_content_type")
    val brandedContentType: Int,
    @SerialName("with_comment_filter_words")
    val withCommentFilterWords: Boolean
)