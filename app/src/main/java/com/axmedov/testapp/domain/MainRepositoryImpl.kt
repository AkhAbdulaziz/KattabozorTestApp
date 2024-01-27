package com.axmedov.testapp.domain

import com.axmedov.testapp.data.remote.MainService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository {

}