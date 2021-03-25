package com.example.fitnessapp.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExerciseContainer(val exercises: List<Exercise>)

@JsonClass(generateAdapter = true)
data class Exercise(
    val id: Int,
    val name: String,
    val category: Category,
    val description: String,
    val muscles: List<Muscle>,
    val muscles_secondary: List<MusclesSecondary>,
    val equipment: List<Equipment>,
    val images: List<Image>
)