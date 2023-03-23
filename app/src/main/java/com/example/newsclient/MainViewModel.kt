package com.example.newsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.domain.StatisticItem
import com.example.newsclient.ui.NavigationItem

class MainViewModel : ViewModel() {

    fun initList() =  mutableListOf<FeedPost>().apply {
        repeat(5) {
            add(
                FeedPost(id = it)
            )
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPost>>(initList())
    val feedPosts: LiveData<List<FeedPost>> = _feedPosts

    private val _selectedScreen = MutableLiveData<NavigationItem>(NavigationItem.Home)
    val selectedScreen: LiveData<NavigationItem> = _selectedScreen

    fun changeScreen(screen: NavigationItem) {
        _selectedScreen.value = screen
    }

    fun changePostStats(feedPost: FeedPost, item: StatisticItem) {
        val oldPosts = _feedPosts.value?.toMutableList() ?: mutableListOf()
        val newFeedPost = updateCount(feedPost, item)
        _feedPosts.value = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
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
        val modifiedList = _feedPosts.value?.toMutableList() ?: mutableListOf()
        modifiedList.remove(item)
        _feedPosts.value = modifiedList
    }

}
