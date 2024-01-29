package com.axmedov.testapp.domain

import com.axmedov.testapp.data.responses.ResponseData
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProducts(): Flow<Result<ResponseData>>
}