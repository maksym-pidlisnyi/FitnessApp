package com.example.fitnessapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.FragmentSetupBinding
import com.example.fitnessapp.util.Constants.Companion.KEY_FIRST_TIME_TOGGLE
import com.example.fitnessapp.util.Constants.Companion.KEY_NAME
import com.example.fitnessapp.util.Constants.Companion.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {

    @Inject
    lateinit var sharedPref : SharedPreferences

    @set:Inject
    var isFirstAppOpen = true

    private lateinit var binding: FragmentSetupBinding

    // For testing, will remove soon
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSetupBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isFirstAppOpen) {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.setupFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_setupFragment_to_runFragment,
                savedInstanceState,
                navOptions
            )
        }

        binding.tvContinue.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success)
                findNavController().navigate(R.id.action_setupFragment_to_runFragment)
            else
                Snackbar.make(requireView(),
                    "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
        }
    }

    /**
     * Saves the name and the weight in shared preferences
     */
    private fun writePersonalDataToSharedPref(): Boolean {
        val name = binding.etName.text.toString()
        val weightText = binding.etWeight.text.toString()
        if (name.isEmpty() || weightText.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(KEY_NAME, name)
            .putFloat(KEY_WEIGHT, weightText.toFloat())
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        val toolbarText = "Let's go, $name!"
        requireActivity().findViewById<TextView>(R.id.tvToolbarTitle).text = toolbarText // TODO check is it good practice


        return true
    }
}