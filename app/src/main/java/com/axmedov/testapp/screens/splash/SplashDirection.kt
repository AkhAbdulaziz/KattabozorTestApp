package com.axmedov.testapp.screens.splash

import com.axmedov.testapp.navigation.AppNavigation
import com.axmedov.testapp.screens.home.HomeScreen
import javax.inject.Inject

class SplashDirection @Inject constructor(
    private val appNavigation: AppNavigation
) {
    suspend fun navigateToHomeScreen() {
        appNavigation.replaceTo(HomeScreen())
    }
}
