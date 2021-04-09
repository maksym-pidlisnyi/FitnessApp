package com.example.fitnessapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fitnessapp.R
import com.example.fitnessapp.adapters.ExerciseAdapter
import com.example.fitnessapp.databinding.FragmentExercisesBinding
import com.example.fitnessapp.util.ConnectionLiveData
import com.example.fitnessapp.util.FragmentBinding
import com.example.fitnessapp.util.isConnected
import com.example.fitnessapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExercisesFragment : Fragment(R.layout.fragment_exercises) {

    // TODO create separaate viewmodel
    private val viewModel: MainViewModel by viewModels()
    private val binding by FragmentBinding<FragmentExercisesBinding>(R.layout.fragment_exercises)

    protected lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        connectionLiveData = context?.let { ConnectionLiveData(it) }!!

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = ExerciseAdapter(ExerciseAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        binding.adapter = adapter
        binding.rvExercises.layoutManager = LinearLayoutManager(requireContext())

        viewModel.exercises.observe(viewLifecycleOwner, {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                        ExercisesFragmentDirections.actionExercisesFragmentToExerciseDetailFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        connectionLiveData.observe(viewLifecycleOwner, {
            viewModel.isNetworkAvailable.value = it
        })
        viewModel.isNetworkAvailable.value = requireContext().isConnected



        return binding.root
    }

}