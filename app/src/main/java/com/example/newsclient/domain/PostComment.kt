package com.example.newsclient.domain

import com.example.newsclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.ic_profile_male,
    val commentText: String = "Long comment text",
    val publicationDate: String = "14:00"
)
