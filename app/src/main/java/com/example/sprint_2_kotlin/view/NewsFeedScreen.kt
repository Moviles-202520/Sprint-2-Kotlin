package com.example.sprint_2_kotlin.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sprint_2_kotlin.viewmodel.NewsFeedViewModel

@Composable
fun NewsFeedScreen(
    viewModel: NewsFeedViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val newsItems by viewModel.newsItems.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(newsItems) { item ->
            NewsCard(item = item, viewModel = viewModel)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
