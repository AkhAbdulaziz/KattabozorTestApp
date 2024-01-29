package com.axmedov.testapp.navigation

import cafe.adriel.voyager.androidx.AndroidScreen

interface AppNavigation {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun replaceTo(screen: AppScreen)
    suspend fun back()
}

typealias AppScreen = AndroidScreen
