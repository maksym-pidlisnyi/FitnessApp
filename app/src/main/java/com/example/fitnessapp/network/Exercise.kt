package com.example.fitnessapp.network

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Exercise(
        val id: String,
        val name: String,
        val description: String,
        val category: String,
        val imagePrimary: String,
        val imageSecondary: String,
        val equipment: String,
        val difficulty: String
) : Parcelable