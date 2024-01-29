package com.axmedov.testapp.screens.home

import com.axmedov.testapp.data.responses.Offer
import com.axmedov.testapp.data.responses.ResponseData
import kotlinx.coroutines.flow.StateFlow

interface HomeViewModel {
    val productsState: StateFlow<ResponseData>
    val progressState: StateFlow<Boolean>
    val errorState: StateFlow<String>

    fun getProducts()
    fun navigateToDetailsScreen(data: Offer)
}
