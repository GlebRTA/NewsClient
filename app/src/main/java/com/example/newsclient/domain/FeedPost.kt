package com.example.newsclient.domain

import com.example.newsclient.R

data class FeedPost(
    val id: Int,
    val communityName: String = "KotlinDevs",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.ic_avatar,
    val contentText: String = "Content TExt",
    val postImgId: Int = R.drawable.ic_meme,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, count = 966),
        StatisticItem(type = StatisticType.SHARES, count = 7),
        StatisticItem(type = StatisticType.COMMENTS, count = 8),
        StatisticItem(type = StatisticType.LIKES, count = 27),
    )

)
