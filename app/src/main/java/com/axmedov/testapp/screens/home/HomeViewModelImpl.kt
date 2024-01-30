package com.axmedov.testapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axmedov.testapp.data.responses.Offer
import com.axmedov.testapp.data.responses.ResponseData
import com.axmedov.testapp.domain.MainRepository
import com.axmedov.testapp.utils.isConnected
import com.axmedov.testapp.utils.setInternetHomeConnectionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val mainRepository: MainRepository,
    private val homeDirection: HomeDirection
) : HomeViewModel, ViewModel() {

    override val productsState = MutableStateFlow<ResponseData>(ResponseData(emptyList()))
    override val progressState = MutableStateFlow<Boolean>(true)
    override val errorState = MutableStateFlow<String>("")

    init {
        getProducts()
        setInternetHomeConnectionListener { isInternetConnected ->
            if (isInternetConnected) {
                getProducts()
            }
        }
    }

    override fun getProducts() {
        progressState.value = true
        if (!isConnected()) {
            progressState.value = false
        } else {
            mainRepository.getProducts().onEach {
                it.onSuccess {
                    productsState.value = it
                    progressState.value = false
                }
                it.onFailure {
                    errorState.value = it.message.toString()
                    progressState.value = false
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun navigateToDetailsScreen(data: Offer) {
        viewModelScope.launch {
            homeDirection.navigateToDetailsScreen(data)
        }
    }
}
