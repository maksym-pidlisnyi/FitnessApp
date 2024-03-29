package com.example.fitnessapp.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.fitnessapp.db.Database
import com.example.fitnessapp.db.ExerciseDao
import com.example.fitnessapp.db.RunDao
import com.example.fitnessapp.util.Constants.Companion.DATABASE_NAME
import com.example.fitnessapp.util.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.example.fitnessapp.util.Constants.Companion.KEY_NAME
import com.example.fitnessapp.util.Constants.Companion.KEY_WEIGHT
import com.example.fitnessapp.util.Constants.Companion.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPreferences: SharedPreferences) =
        sharedPreferences.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPreferences: SharedPreferences) = sharedPreferences.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true
    )

    @Singleton
    @Provides
    fun provideAppDb(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideRunDao(db: Database): RunDao {
        return db.getRunDao()
    }

    @Singleton
    @Provides
    fun provideExercisesDao(db: Database): ExerciseDao {
        return db.getExercisesDao()
    }

}