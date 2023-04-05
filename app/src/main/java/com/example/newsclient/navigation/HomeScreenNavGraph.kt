package com.example.newsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.HomeScreenNavGrapph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit,
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Main.route
    ) {
        composable(Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }

        composable(Screen.Comments.route) {
            commentsScreenContent()
        }
    }
}