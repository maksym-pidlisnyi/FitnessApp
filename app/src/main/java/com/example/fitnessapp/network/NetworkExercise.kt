package com.example.fitnessapp.network

import android.os.Parcelable
import com.example.fitnessapp.db.DatabaseExercise
import com.example.fitnessapp.util.Helper
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class NetworkExercise(
        val _id: String,
        val name: String,
        val description: String,
        val category: String,
        val imagePrimary: String,
        val imageSecondary: String,
        val equipment: String,
        val difficulty: String
) : Parcelable

fun List<NetworkExercise>.asDatabaseModel(): Array<DatabaseExercise> {
    return map {
        DatabaseExercise(
                id = it._id,
                name = it.name,
                description = it.description,
                category = it.category,
                imagePrimary = Helper.recoverImageFromUrl(it.imagePrimary),
                imageSecondary = Helper.recoverImageFromUrl(it.imageSecondary),
                equipment = it.equipment,
                difficulty = it.difficulty
        )
    }.toTypedArray()
}