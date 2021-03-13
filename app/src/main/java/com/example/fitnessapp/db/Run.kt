package com.example.fitnessapp.db

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "running_table")
data class Run(
        var img: Bitmap? = null,
        var timestamp: Long = 0L,
        var avgSpeedInKMH: Float = 0f,
        var distanceInMeters: Int = 0,
        var timeInMillis: Long = 0L,
        var caloriesBurned: Int = 0
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}