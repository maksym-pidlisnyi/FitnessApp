package com.example.fitnessapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentExerciseDetailBinding
import com.example.fitnessapp.util.FragmentBinding
import com.example.fitnessapp.viewmodels.DetailViewModel
import com.example.fitnessapp.viewmodels.DetailViewModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExerciseDetailFragment : Fragment(R.layout.fragment_exercise_detail) {

    private val binding by FragmentBinding<FragmentExerciseDetailBinding>(R.layout.fragment_exercise_detail)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val exercise = ExerciseDetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(exercise, application)
        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }
}