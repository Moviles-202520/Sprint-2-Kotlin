package com.example.sprint_2_kotlin.model.data

import kotlinx.serialization.Serializable

@Serializable
data class NewsItem(
    val newsItemId: Int = 0,
    val userProfileId: Int = 0,
    val title: String = "",
    val shortDescription: String = "",
    val imageUrl: String = "",
    val categoryId: Int = 0,
    val authorType: String = "",
    val authorInstitution: String = "",
    val daysSince: Int = 0,
    val averageReliabilityScore: Double = 0.00,
    val isFake: Boolean = false,
    val isVerifiedSource: Boolean = false,
    val isVerifiedData: Boolean = false,
    val isRecognizedAuthor: Boolean = false,
    val isManipulated: Boolean = false,
    val longDescription: String = "",
    val originalSourceUrl: String = "",
    val publicationDate: String = "",
    val addedToAppDate: String = "",
    val totalRatings: Int = 0
)

