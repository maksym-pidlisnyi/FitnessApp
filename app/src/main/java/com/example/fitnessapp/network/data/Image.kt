package com.example.fitnessapp.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val id: Int,
    val exercise: Int,
    val image: String,
    val status: String,
    val is_main: Boolean
)