package com.axmedov.testapp.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val direction: SplashDirection
) : SplashViewModel, ViewModel() {

    init {
        viewModelScope.launch {
            delay(1000L)
            direction.navigateToHomeScreen()
        }
    }
}