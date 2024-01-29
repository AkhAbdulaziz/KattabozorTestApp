package com.axmedov.testapp.screens.activity

import androidx.lifecycle.ViewModel
import com.axmedov.testapp.utils.checkInternetConnection
import com.axmedov.testapp.utils.setInternetConnectionListener
import com.axmedov.testapp.utils.setShowMessageOnTopOfScreenListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModelImpl @Inject constructor(

) : MainActivityViewModel, ViewModel() {
    override val internetConnectionLiveData = MutableStateFlow<Boolean>(true)
    override val showMessageOnTopOfScreenLiveData = MutableStateFlow<String>("")

    init {
        checkInternetConnection()
        setInternetConnectionListener { isInternetConnected ->
            internetConnectionLiveData.value = isInternetConnected
        }
        setShowMessageOnTopOfScreenListener { message ->
            showMessageOnTopOfScreenLiveData.value = message
        }
    }
}
