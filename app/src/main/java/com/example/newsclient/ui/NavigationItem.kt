package com.example.newsclient.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsclient.R
import com.example.newsclient.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val icon: ImageVector,
    val titleResId: Int
) {

    object Home : NavigationItem(
        screen = Screen.Home,
        icon = Icons.Outlined.Home,
        titleResId = R.string.navigation_home
    )

    object Favorites : NavigationItem(
        screen = Screen.Favorite,
        icon = Icons.Outlined.Favorite,
        titleResId = R.string.navigation_favorite
    )

    object Profile : NavigationItem(
        screen = Screen.Profile,
        icon = Icons.Outlined.Person,
        titleResId = R.string.navigation_favorite
    )
}