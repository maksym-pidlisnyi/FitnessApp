package com.example.fitnessapp.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Exercise(
        val id: String,
        val name: String,
        val description: String,
        val category: String,
        val imagePrimary: String,
        val imageSecondary: String,
        val equipment: String,
        val difficulty: String
)