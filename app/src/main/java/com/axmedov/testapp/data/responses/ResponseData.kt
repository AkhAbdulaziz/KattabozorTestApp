package com.axmedov.testapp.data.responses

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseData(
    @SerializedName("offers")
    val offers: List<Offer?>?
)

