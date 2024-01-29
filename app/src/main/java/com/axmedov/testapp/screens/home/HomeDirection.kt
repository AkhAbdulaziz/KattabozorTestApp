package com.axmedov.testapp.screens.home

import com.axmedov.testapp.data.responses.Offer
import com.axmedov.testapp.navigation.AppNavigation
import com.axmedov.testapp.screens.details.DetailsScreen
import javax.inject.Inject

class HomeDirection @Inject constructor(
    private val appNavigation: AppNavigation
) {
    suspend fun navigateToDetailsScreen(data: Offer) {
        appNavigation.navigateTo(DetailsScreen(data = data))
    }
}