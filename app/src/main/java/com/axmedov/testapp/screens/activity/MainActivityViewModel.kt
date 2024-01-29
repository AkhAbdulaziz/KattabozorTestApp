package com.axmedov.testapp.screens.activity

import kotlinx.coroutines.flow.StateFlow

interface MainActivityViewModel {
    val internetConnectionLiveData: StateFlow<Boolean>
    val showMessageOnTopOfScreenLiveData: StateFlow<String>
}