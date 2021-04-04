package com.example.fitnessapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
        entities = [Run::class, DatabaseExercise::class],
        version = 3
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun getRunDao(): RunDao
    abstract fun getExercisesDao(): ExerciseDao
}