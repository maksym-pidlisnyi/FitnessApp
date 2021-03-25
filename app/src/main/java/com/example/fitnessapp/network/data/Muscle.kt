package com.example.fitnessapp.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Muscle(
    val id: Int,
    val name: String,
    val is_front: Boolean
)