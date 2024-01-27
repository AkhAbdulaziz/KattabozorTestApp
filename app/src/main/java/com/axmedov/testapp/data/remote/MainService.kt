package com.axmedov.testapp.data.remote

import com.axmedov.testapp.data.responses.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface MainService {

    @GET("hh/test/api/v1/offers")
    suspend fun getDataList(): Response<ResponseData>
}
