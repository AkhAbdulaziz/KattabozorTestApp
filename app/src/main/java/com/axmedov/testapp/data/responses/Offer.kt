package com.axmedov.testapp.data.responses

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class Offer(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("merchant")
    val merchant: String?,
    @SerializedName("attributes")
    val attributes: List<Attribute?>?,
    @SerializedName("image")
    val image: Image?
) : Serializable