package com.example.fitnessapp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData

// TODO boilerplate code, redo in future
class ConnectionLiveData(private val context: Context) : LiveData<Boolean>() {

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            postValue(context.isConnected)
        }
    }

    override fun onActive() {
        super.onActive()
        context.registerReceiver(
                networkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onInactive() {
        super.onInactive()
        try {
            context.unregisterReceiver(networkReceiver)
        } catch (e: Exception) {
        }
    }
}

val Context.isConnected: Boolean
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo?.isConnected == true