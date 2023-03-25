package com.example.newsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.PostComment
import com.example.newsclient.domain.StatisticItem
import com.example.newsclient.ui.HomeScreenState

class MainViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(5) {
            add(
                FeedPost(id = it)
            )
        }
    }

    private val comments = mutableListOf<PostComment>().apply {
        repeat(15) {
            add(
                PostComment(id = it)
            )
        }
    }

    private val initialState = HomeScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState: LiveData<HomeScreenState> = _screenState

    private var stateSaver: HomeScreenState? = initialState
    fun showComments(feedPost: FeedPost) {
        stateSaver = _screenState.value
        _screenState.value = HomeScreenState.Comments(feedPost = feedPost, comments = comments)
    }


    fun closeComments() {
        _screenState.value = stateSaver
    }

    fun changePostStats(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val newFeedPost = updateCount(feedPost, item)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = HomeScreenState.Posts(posts = newPosts)
    }

    private fun updateCount(feedPost: FeedPost, item: StatisticItem): FeedPost {
        val oldStatistic = feedPost.statistics
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        return feedPost.copy(statistics = newStatistic)
    }

    fun delete(item: FeedPost) {
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return

        val modifiedList = currentState.posts.toMutableList()
        modifiedList.remove(item)
        _screenState.value = HomeScreenState.Posts(posts = modifiedList)
    }

}
