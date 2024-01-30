package com.axmedov.testapp.data.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class ResponseData(
    @SerializedName("offers")
    val offers: List<Offer?>?
): Serializable
