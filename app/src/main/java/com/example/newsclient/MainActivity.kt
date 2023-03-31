package com.example.newsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.newsclient.ui.theme.MainScreen
import com.example.newsclient.ui.theme.NewsClientTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsClientTheme {
                MainScreen()
            }
        }
    }
}