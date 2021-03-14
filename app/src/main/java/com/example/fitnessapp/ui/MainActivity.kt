package com.example.fitnessapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.util.Constants.Companion.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO replace with cleaner code in future
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)  // possible bug(maybe put in the end)

        binding.apply {
            lifecycleOwner = this@MainActivity
            setSupportActionBar(toolbar)
            val navController = (supportFragmentManager
                    .findFragmentById(R.id.navHostFragment) as NavHostFragment)
                    .navController
            bottomNavigationView.setupWithNavController(navController)
            bottomNavigationView.setOnNavigationItemReselectedListener { /* do nothing */ }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                        bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT)
            binding.navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
    }
}