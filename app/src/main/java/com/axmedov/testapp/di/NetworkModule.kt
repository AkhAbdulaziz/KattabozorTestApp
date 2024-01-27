package com.axmedov.testapp.di

import com.axmedov.testapp.data.remote.ApiClient
import com.axmedov.testapp.data.remote.MainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getMainService(): MainService = ApiClient.retrofit.create(MainService::class.java)
}
