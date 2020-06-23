package br.com.vegait.helpdeliveryman.data.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("description")
    val description: String
)