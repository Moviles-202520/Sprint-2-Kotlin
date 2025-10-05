package com.example.sprint_2_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sprint_2_kotlin.model.data.NewsItem
import com.example.sprint_2_kotlin.model.data.RatingItem
import com.example.sprint_2_kotlin.model.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsItemDetailViewModel : ViewModel() {

    private val repository = Repository()

    private val _newsItem = MutableStateFlow<NewsItem?>(null)
    val newsItem = _newsItem.asStateFlow()

    private val _ratings = MutableStateFlow<List<RatingItem>>(emptyList())
    val ratings = _ratings.asStateFlow()

    fun loadNewsItem(item: NewsItem) {
        _newsItem.value = item
        loadRatings(item.news_item_id)
    }

    private fun loadRatings(newsItemId: Int) {
        viewModelScope.launch {
            try {
                // TODO: Replace with actual Supabase query when endpoint is ready
                // For now, use mock data for visualization
                _ratings.value = listOf(
                    RatingItem(
                        rating_item_id = 1,
                        news_item_id = newsItemId,
                        assigned_reliability_score = 0.85,
                        comment_text = "Solid reporting, clear sources.",
                        rating_date = "2025-09-29"
                    ),
                    RatingItem(
                        rating_item_id = 2,
                        news_item_id = newsItemId,
                        assigned_reliability_score = 0.45,
                        comment_text = "Seems biased in interpretation.",
                        rating_date = "2025-10-01"
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /***private fun loadRatings(newsItemId: Int) {
        viewModelScope.launch {
            try {
                val fetchedRatings = repository.getRatingsForNewsItem(newsItemId)
                _ratings.value = fetchedRatings
            } catch (e: Exception) {
                e.printStackTrace()
                _ratings.value = emptyList()
            }
        }
    }***/

}

