package com.example.sprint_2_kotlin.model.data

import kotlinx.serialization.Serializable


@Serializable
data class UserSession(
    val userSessionId: Int = 0,
    val userProfileId: Int = 0,
    val startTime: String = "",
    val endTime: String = "",
    val durationSeconds: Int = 0,
    val deviceType: String = "",
    val operatingSystem: String = "",
    val usedCategoryFilter: Boolean = false,
    val articlesViewed: Int = 0
)
