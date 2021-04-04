package com.example.fitnessapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Query("select * from exercises_table")
    fun getExercises(): LiveData<List<DatabaseExercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg exercise: DatabaseExercise)
}