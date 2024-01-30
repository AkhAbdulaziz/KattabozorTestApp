package com.axmedov.testapp.data.responses

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Attribute(
    @SerializedName("name")
    val name: String?,
    @SerializedName("value")
    val value: String?
): Serializable