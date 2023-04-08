package com.example.newsclient.ui

import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.PostComment

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()

}
