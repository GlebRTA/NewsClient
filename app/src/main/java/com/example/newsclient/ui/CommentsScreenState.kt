package com.example.newsclient.ui

import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}
