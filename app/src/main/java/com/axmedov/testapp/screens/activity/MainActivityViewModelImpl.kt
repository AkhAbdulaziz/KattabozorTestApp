package com.axmedov.testapp.screens.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axmedov.testapp.utils.checkInternetConnection
import com.axmedov.testapp.utils.setInternetMainActivityConnectionListener
import com.axmedov.testapp.utils.setShowMessageOnTopOfScreenListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModelImpl @Inject constructor(

) : MainActivityViewModel, ViewModel() {
    override val internetConnectionState = MutableStateFlow<Boolean>(true)
    override val showMessageOnTopOfScreenState = MutableStateFlow<String>("")

    init {
        checkInternetConnection()
        setInternetMainActivityConnectionListener { isInternetConnected ->
            viewModelScope.launch {
                internetConnectionState.value = isInternetConnected
            }
        }
        setShowMessageOnTopOfScreenListener { message ->
            viewModelScope.launch {
                showMessageOnTopOfScreenState.value = message
            }
        }
    }
}
