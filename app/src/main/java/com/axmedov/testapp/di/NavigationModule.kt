package com.axmedov.testapp.di

import com.axmedov.testapp.navigation.AppNavigation
import com.axmedov.testapp.navigation.NavigationDispatcher
import com.axmedov.testapp.navigation.NavigationHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindNavigationHandler(dispatcher: NavigationDispatcher): NavigationHandler

    @Binds
    fun bindAppNavigation(dispatcher: NavigationDispatcher): AppNavigation
}