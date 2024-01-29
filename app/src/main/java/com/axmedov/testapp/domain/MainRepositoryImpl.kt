package com.axmedov.testapp.domain

import com.axmedov.testapp.data.remote.MainService
import com.axmedov.testapp.data.responses.ResponseData
import com.axmedov.testapp.utils.timber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository {

    override fun getProducts(): Flow<Result<ResponseData>> = flow {
        val response = mainService.getProducts()

        if (response.isSuccessful && response.body() != null) {
            emit(Result.success(response.body()!!))
        } else {
            emit(Result.failure(Throwable("")))
        }
    }.catch {
        timber(it.message.toString())
    }.flowOn(Dispatchers.IO)
}