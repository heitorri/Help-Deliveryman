package br.com.vegait.helpdeliveryman.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class DataItem(
    @SerializedName("min_temp")
    val minTemp: String,
    @SerializedName("weather")
    val weather: Weather,
    @SerializedName("max_temp")
    val maxTemp: String,
    @SerializedName("datetime")
    val date: Date
)