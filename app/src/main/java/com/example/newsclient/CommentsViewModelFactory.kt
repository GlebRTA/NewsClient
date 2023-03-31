package com.example.newsclient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsclient.domain.FeedPost

class CommentsViewModelFactory(
    private val feedPost: FeedPost
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentsViewModel::class.java)) {
            return CommentsViewModel(feedPost) as T
        }
        throw RuntimeException("Unknown ViewModel class $modelClass")
    }
}