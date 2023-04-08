package com.example.newsclient.navigation

import com.example.newsclient.domain.FeedPost
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)
    object Home : Screen(ROUTE_HOME_SCREEN)
    object Comments : Screen(ROUTE_COMMENTS) {
        private const val ROUTE_COMMENTS_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_COMMENTS_ARGS/${feedPostJson}"
        }
    }

    companion object {
        const val KEY_FEED_POST = "feed_post"

        private const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        private const val ROUTE_HOME_SCREEN = "home"
        private const val ROUTE_NEWS_FEED = "news_feed"
        private const val ROUTE_FAVORITE = "favorite"
        private const val ROUTE_PROFILE = "profile"
    }
}
