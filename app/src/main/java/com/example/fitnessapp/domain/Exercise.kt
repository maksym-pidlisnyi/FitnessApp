package com.example.fitnessapp.domain

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exercise(
        val id: String,
        val name: String,
        val description: String,
        val category: String,
        val imagePrimary: Bitmap,
        val imageSecondary: Bitmap,
        val equipment: String,
        val difficulty: String
) : Parcelable