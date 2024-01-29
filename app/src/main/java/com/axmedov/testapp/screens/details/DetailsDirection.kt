package com.axmedov.testapp.screens.details

import com.axmedov.testapp.navigation.AppNavigation
import javax.inject.Inject

class DetailsDirection @Inject constructor(
    private val appNavigation: AppNavigation
) {
    suspend fun back() {
        appNavigation.back()
    }
}
