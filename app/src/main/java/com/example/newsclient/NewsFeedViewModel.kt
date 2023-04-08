package com.example.newsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.StatisticItem
import com.example.newsclient.ui.NewsFeedScreenState

class NewsFeedViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(5) {
            add(
                FeedPost(
                    id = it,
                    contentText = "Content NO $it"
                )
            )
        }
    }

    private val initialState = NewsFeedScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    fun changePostStats(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return

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
        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
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
        if (currentState !is NewsFeedScreenState.Posts) return

        val modifiedList = currentState.posts.toMutableList()
        modifiedList.remove(item)
        _screenState.value = NewsFeedScreenState.Posts(posts = modifiedList)
    }

}
