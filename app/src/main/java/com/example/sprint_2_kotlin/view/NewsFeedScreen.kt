package com.example.sprint_2_kotlin.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sprint_2_kotlin.model.data.NewsItem
import com.example.sprint_2_kotlin.viewmodel.NewsFeedViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val newsItems by viewModel.newsItems.collectAsState()
    var selectedNewsItem by remember { mutableStateOf<NewsItem?>(null) }

    if (selectedNewsItem == null) {
        // Show feed
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(newsItems) { item ->
                NewsCard(
                    item = item,
                    viewModel = viewModel,
                    onClick = { selectedNewsItem = item } // 👈 set selected item
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    } else {
        // Show detail
        NewsItemDetailScreen(
            newsItem = selectedNewsItem!!,
        )

        // Optional back button overlay
        IconButton(
            onClick = { selectedNewsItem = null },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}
