package com.example.sprint_2_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import com.example.sprint_2_kotlin.ui.theme.Sprint2KotlinTheme
import com.example.sprint_2_kotlin.view.AuthScreen
import com.example.sprint_2_kotlin.view.NewsFeedScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sprint2KotlinTheme {
                var loggedIn by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (loggedIn) {
                        NewsFeedScreen(modifier = Modifier.padding(innerPadding))
                    } else {
                        AuthScreen(onLoginSuccess = { loggedIn = true })
                    }
                }
            }
        }
    }
}


/***class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sprint2KotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsFeedScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}***/
