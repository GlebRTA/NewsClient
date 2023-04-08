package com.example.newsclient.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsclient.domain.FeedPost
import com.example.newsclient.navigation.AppNavGraph
import com.example.newsclient.navigation.Screen
import com.example.newsclient.navigation.rememberNavigationState
import com.example.newsclient.ui.CommentsScreen
import com.example.newsclient.ui.HomeScreen
import com.example.newsclient.ui.NavigationItem

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorites,
                    NavigationItem.Profile
                )

                items.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
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
            newsFeedScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = { feedPost ->
                        navigationState.navigateToComments(feedPost)
                    }
                )
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackListener = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
                )
            },
            favoriteScreenContent = { Text(text = "Favorites") },
            profileScreenContent = { Text(text = "Profile") }
        )
    }
}
