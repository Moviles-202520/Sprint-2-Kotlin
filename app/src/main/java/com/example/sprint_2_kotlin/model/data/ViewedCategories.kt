package com.example.sprint_2_kotlin.model.data

import kotlinx.serialization.Serializable

@Serializable
data class ViewedCategories(
    val categoryId: Int = 0,
    val userSessionId: Int = 0
)
