package com.example.sprint_2_kotlin.model.data

import kotlinx.serialization.Serializable

@Serializable
data class RatingItem(
    val ratingItemId: Int = 0,
    val newsItemId: Int = 0,
    val userProfileId: Int = 0,
    val assignedReliabilityScore: Double = 0.00,
    val commentText: String = "",
    val ratingDate: String = "",
    val isCompleted: Boolean = false
)