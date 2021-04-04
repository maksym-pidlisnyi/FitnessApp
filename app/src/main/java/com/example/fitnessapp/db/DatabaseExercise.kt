package com.example.fitnessapp.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitnessapp.domain.Exercise

@Entity(tableName = "exercises_table")
data class DatabaseExercise constructor(
        @PrimaryKey
        val id: String,
        val name: String,
        val description: String,
        val category: String,
        val imagePrimary: Bitmap,
        val imageSecondary: Bitmap,
        val equipment: String,
        val difficulty: String
)

fun List<DatabaseExercise>.asDomainModel(): List<Exercise> {
    return map {
        Exercise(
                id = it.id,
                name = it.name,
                description = it.description,
                category = it.category,
                imagePrimary = it.imagePrimary,
                imageSecondary = it.imageSecondary,
                equipment = it.equipment,
                difficulty = it.difficulty
        )
    }
}