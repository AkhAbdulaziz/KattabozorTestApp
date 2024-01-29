package com.axmedov.testapp.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationDispatcher @Inject constructor() : AppNavigation, NavigationHandler {
    override val navigationBuffer = MutableSharedFlow<NavigationArgs>()

    private suspend fun navigator(args: NavigationArgs) {
        navigationBuffer.emit(args)
    }

    override suspend fun navigateTo(screen: AppScreen) = navigator {
        push(screen)
    }

    override suspend fun replaceTo(screen: AppScreen) = navigator {
        replace(screen)
    }

    override suspend fun back() = navigator {
        pop()
    }
}
