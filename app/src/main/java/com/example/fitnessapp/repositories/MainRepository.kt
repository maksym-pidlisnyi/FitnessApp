package com.example.fitnessapp.repositories

import com.example.fitnessapp.db.Run
import com.example.fitnessapp.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
        val runDao: RunDao
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

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