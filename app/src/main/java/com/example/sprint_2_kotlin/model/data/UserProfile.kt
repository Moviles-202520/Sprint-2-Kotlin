package com.example.sprint_2_kotlin.model.data

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val userProfileId: Int = 0,
    val userAuthId: String = "",
    val userAuthEmail: String = ""
)
