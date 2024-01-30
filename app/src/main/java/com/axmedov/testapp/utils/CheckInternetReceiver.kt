package com.axmedov.testapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

private var internetHomeConnectionListener: ((Boolean) -> Unit)? = null
fun setInternetHomeConnectionListener(f: (Boolean) -> Unit) {
    internetHomeConnectionListener = f
}

private var internetMainActivityConnectionListener: ((Boolean) -> Unit)? = null
fun setInternetMainActivityConnectionListener(f: (Boolean) -> Unit) {
    internetMainActivityConnectionListener = f
}

fun checkInternetConnection() {
    timber("Called checkInternetConnection() method. Result=${isConnected()}", "sdjskjdksjd")
    internetHomeConnectionListener?.invoke(isConnected())
    internetMainActivityConnectionListener?.invoke(isConnected())
}

@AndroidEntryPoint
class CheckInternetReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        checkInternetConnection()
    }
}
