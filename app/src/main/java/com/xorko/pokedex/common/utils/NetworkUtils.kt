package com.xorko.pokedex.common.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import org.koin.java.KoinJavaComponent.inject


object NetworkUtils {

    private val context: Context by inject(Context::class.java)

    fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}