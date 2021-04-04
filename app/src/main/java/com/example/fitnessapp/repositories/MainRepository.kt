package com.example.fitnessapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.fitnessapp.db.ExerciseDao
import com.example.fitnessapp.db.Run
import com.example.fitnessapp.db.RunDao
import com.example.fitnessapp.db.asDomainModel
import com.example.fitnessapp.domain.Exercise
import com.example.fitnessapp.network.ExerciseService
import com.example.fitnessapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
        private val runDao: RunDao,
        private val exerciseDao: ExerciseDao,
        private val exerciseService: ExerciseService
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    val exercises: LiveData<List<Exercise>> = Transformations.map(exerciseDao.getExercises()) {
        it.asDomainModel()
    }

    suspend fun refreshExercises() {
        withContext(Dispatchers.IO) {
            val training = exerciseService.getExercises().await()
            exerciseDao.insertAll(*training.asDatabaseModel())
        }
    }
//    suspend fun getAllExercises() = exerciseService.getExercises().await()

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