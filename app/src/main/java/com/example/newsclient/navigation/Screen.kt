package com.example.newsclient.navigation

sealed class Screen(
    val route: String
) {

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)
    object Main : Screen(ROUTE_MAIN_SCREEN)
    object Comments : Screen(ROUTE_COMMENTS)

    private companion object {
        private const val ROUTE_COMMENTS = "comments"
        private const val ROUTE_MAIN_SCREEN = "main"
        private const val ROUTE_NEWS_FEED = "news_feed"
        private const val ROUTE_FAVORITE = "favorite"
        private const val ROUTE_PROFILE = "profile"
    }
}
