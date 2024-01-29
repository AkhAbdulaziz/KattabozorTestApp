package com.axmedov.testapp.navigation

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.Flow

interface NavigationHandler {
    val navigationBuffer: Flow<NavigationArgs>
}

typealias NavigationArgs = Navigator.() -> Unit
