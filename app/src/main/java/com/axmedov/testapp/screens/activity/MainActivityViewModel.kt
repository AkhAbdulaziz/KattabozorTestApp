package com.axmedov.testapp.screens.activity

import kotlinx.coroutines.flow.StateFlow

interface MainActivityViewModel {
    val internetConnectionState: StateFlow<Boolean>
    val showMessageOnTopOfScreenState: StateFlow<String>
}
