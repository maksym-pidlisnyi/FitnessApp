package com.example.fitnessapp.repositories

import com.example.fitnessapp.db.Run
import com.example.fitnessapp.db.RunDao
import com.example.fitnessapp.network.ExerciseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val runDao: RunDao,
    private val exerciseService: ExerciseService
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    suspend fun getAllExercises() {
        withContext(Dispatchers.IO) {
            val exercises = exerciseService.getExercises().await()
            // TODO return somewhere
        }

    }

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()
    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTime()
    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()
    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCalories()
    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()
    fun getTotalTimeInMillis() = runDao.getTotalTimeInMills()
    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()
    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()
}