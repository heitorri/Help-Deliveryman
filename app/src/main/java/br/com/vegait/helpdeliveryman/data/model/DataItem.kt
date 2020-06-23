package br.com.vegait.helpdeliveryman.data.model

import com.google.gson.annotations.SerializedName

data class DataItem(
    @SerializedName("min_temp")
    val minTemp: String,
    @SerializedName("weather")
    val weather: Weather,
    @SerializedName("max_temp")
    val maxTemp: String
)