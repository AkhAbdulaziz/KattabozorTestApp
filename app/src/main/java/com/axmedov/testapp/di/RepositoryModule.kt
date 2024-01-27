package com.axmedov.testapp.di

import com.axmedov.testapp.domain.MainRepository
import com.axmedov.testapp.domain.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getMainRepository(repositoryImpl: MainRepositoryImpl): MainRepository
}