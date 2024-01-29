package com.axmedov.testapp.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModelImpl @Inject constructor(
    private val detailsDirection: DetailsDirection
) : DetailsViewModel, ViewModel() {

    override fun back() {
        viewModelScope.launch {
            detailsDirection.back()
        }
    }
}
