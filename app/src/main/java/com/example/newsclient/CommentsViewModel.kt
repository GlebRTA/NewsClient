package com.example.newsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.PostComment
import com.example.newsclient.domain.StatisticItem
import com.example.newsclient.ui.CommentsScreenState
import com.example.newsclient.ui.NewsFeedScreenState

class CommentsViewModel(
    feedPost: FeedPost
) : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(15) {
                add(
                    PostComment(id = it)
                )
            }
        }
        _screenState.value = CommentsScreenState.Comments(feedPost, comments)
    }
}
