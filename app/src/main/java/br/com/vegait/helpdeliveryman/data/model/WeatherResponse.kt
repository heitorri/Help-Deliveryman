package br.com.vegait.helpdeliveryman.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherResponse(
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("data")
    val data: List<DataItem>
)