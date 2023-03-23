package com.example.newsclient.ui.theme

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsclient.MainViewModel
import com.example.newsclient.navigation.AppNavGraph
import com.example.newsclient.navigation.NavigationState
import com.example.newsclient.navigation.rememberNavigationState
import com.example.newsclient.ui.HomeScreen
import com.example.newsclient.ui.NavigationItem

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val navigationState = rememberNavigationState()

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
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            },
            favoriteScreenContent = { Text(text = "Favorites") },
            profileScreenContent = { Text(text = "Profile") }
        )
    }
}
