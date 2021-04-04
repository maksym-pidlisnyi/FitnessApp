package com.example.fitnessapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fitnessapp.domain.Exercise

// TODO remake
class DetailViewModel(@Suppress("UNUSED_PARAMETER") exercise: Exercise, app: Application) :
    AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<Exercise>()

    val selectedProperty: LiveData<Exercise>
        get() = _selectedProperty

    init {
        _selectedProperty.value = exercise
    }

}