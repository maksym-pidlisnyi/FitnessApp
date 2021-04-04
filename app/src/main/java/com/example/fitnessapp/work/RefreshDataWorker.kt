package com.example.fitnessapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.fitnessapp.repositories.MainRepository
import retrofit2.HttpException
import javax.inject.Inject

class RefreshDataWorker @Inject constructor(private val mainRepository: MainRepository,
                                            appContext: Context, params: WorkerParameters) :
        CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            mainRepository.refreshExercises()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}

