package com.example.newsclient.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.navigation.AppNavGraph
import com.example.newsclient.navigation.rememberNavigationState
import com.example.newsclient.ui.CommentsScreen
import com.example.newsclient.ui.HomeScreen
import com.example.newsclient.ui.NavigationItem

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val commentsToPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorites,
                    NavigationItem.Profile
                )

                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRout == item.screen.route,
                        onClick = { navigationState.navigateTo(item.screen.route) },
                        label = { Text(text = stringResource(id = item.titleResId)) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = "Home icon"
                            )
                        },
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary

                    )
                }
            }
        }
    ) { paddingValues ->

        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPost.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentClickListener = {
                            commentsToPost.value = it
                        }
                    )
                } else {
                    CommentsScreen(
                        onBackListener = {
                            commentsToPost.value = null
                        },
                        feedPost = commentsToPost.value!!
                    )
                }
            },
            favoriteScreenContent = { Text(text = "Favorites") },
            profileScreenContent = { Text(text = "Profile") }
        )
    }
}
