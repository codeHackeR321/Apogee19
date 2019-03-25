package com.anenigmatic.apogee19.screens.shared.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Checks the network details.
 * */
class NetworkDetails(private val context: Context) {

    fun isConnectedToInternet(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }
}