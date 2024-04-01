package com.livmas.currency.presentation.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

class ConnectivityReceiver : BroadcastReceiver() {
    companion object {
        private var connectivityReceiverListeners: MutableList<ConnectivityReceiverListener> = mutableListOf()
        fun registerListener(listener: ConnectivityReceiverListener) {
            connectivityReceiverListeners.add(listener)
        }
        fun unregisterListener(listener: ConnectivityReceiverListener) {
            connectivityReceiverListeners.remove(listener)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("Receive", "Connection Received")
        if (isConnected(context))
        connectivityReceiverListeners.forEach { it.onNetworkConnected() }
    }

    private fun isConnected(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netCap = connMgr.getNetworkCapabilities(connMgr.activeNetwork) ?: return false
        return when {
            netCap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            netCap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
    fun interface ConnectivityReceiverListener {
        fun onNetworkConnected()
    }

}