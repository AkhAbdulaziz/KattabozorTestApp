package com.axmedov.testapp.utils

private var showMessageOnTopOfScreenListener: ((String) -> Unit)? = null
fun setShowMessageOnTopOfScreenListener(f: (String) -> Unit) {
    showMessageOnTopOfScreenListener = f
}

fun showMessageOnTopOfScreen(message: String) {
    showMessageOnTopOfScreenListener?.invoke(message)
}