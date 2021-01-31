package com.example.fitnessapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.fitnessapp.repositories.MainRepository

class MainViewModel @ViewModelInject constructor(
        val mainRepository: MainRepository
) : ViewModel() {
}