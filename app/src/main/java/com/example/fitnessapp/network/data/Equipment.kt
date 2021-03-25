package com.example.fitnessapp.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Equipment(
    val id: Int,
    val name: String
)