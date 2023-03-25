package com.example.newsclient.ui

import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.PostComment

sealed class HomeScreenState {

    object Initial : HomeScreenState()

    data class Posts(val posts: List<FeedPost>) : HomeScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : HomeScreenState()
}
