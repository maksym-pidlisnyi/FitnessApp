package com.example.fitnessapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessapp.network.Exercise

class DetailViewModelFactory(
    private val exercise: Exercise,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(exercise, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}