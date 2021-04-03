package com.example.fitnessapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.example.fitnessapp.util.Constants.Companion.ACTION_SHOW_TRACKING_FRAGMENT
import com.example.fitnessapp.util.DrawerLocker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DrawerLocker {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)  // possible bug(maybe put in the end)

        var navController : NavController
        binding.apply {
            lifecycleOwner = this@MainActivity
            setSupportActionBar(toolbar)

            navController = (supportFragmentManager
                    .findFragmentById(R.id.navHostFragment) as NavHostFragment)
                    .navController
            bottomNavigationView.setupWithNavController(navController)
            bottomNavigationView.setOnNavigationItemReselectedListener { /* do nothing */ }
            navView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.settingsFragment, R.id.runFragment, R.id.exercisesFragment,
                    R.id.statisticsFragment -> {
                        bottomNavigationView.visibility = View.VISIBLE
                        navView.visibility = View.VISIBLE
                    }
                    else -> {
                        bottomNavigationView.visibility = View.GONE
                        navView.visibility = View.GONE
                    }
                }
            }

            // TODO maybe refactor in future
            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_exit -> {
                        finish()
                    }
                    R.id.nav_help -> {
                        startActivity(Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://www.google.com/search?q=how+to+google"))
                        )
                        drawerLayout.closeDrawer(navView)
                    }
                    R.id.settingsFragment -> {
                        navController.navigate(R.id.settingsFragment)
                        drawerLayout.closeDrawer(navView)
                    }
                    R.id.runFragment -> {
                        navController.navigate(R.id.runFragment)
                        drawerLayout.closeDrawer(navView)
                    }
                    R.id.statisticsFragment -> {
                        navController.navigate(R.id.statisticsFragment)
                        drawerLayout.closeDrawer(navView)
                    }
                }
                true
            }

            toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT)
            binding.navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
    }

    override fun drawerLocked(shouldLock: Boolean) {
        if (shouldLock) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }
}