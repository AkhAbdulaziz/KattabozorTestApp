package com.axmedov.testapp.data.remote

import com.axmedov.testapp.BuildConfig.BASE_URL
import com.axmedov.testapp.BuildConfig.LOGGING
import com.axmedov.testapp.utils.timber
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getHttpClient())
        .build()

    private fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addLogging()
            .build()
    }
}

fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = HttpLoggingInterceptor.Logger { message -> timber(message, "HTTP") }
    if (LOGGING) {
        addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}
