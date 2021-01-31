package com.example.fitnessapp.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fitnessapp.R
import com.example.fitnessapp.viewmodels.StatisticsViewModel

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: StatisticsViewModel by viewModels()
}