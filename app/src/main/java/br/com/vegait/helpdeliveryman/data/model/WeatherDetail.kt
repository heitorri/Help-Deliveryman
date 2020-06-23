package br.com.vegait.helpdeliveryman.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDetail(
    val icon: String,
    val description: String,
    val minTemp: String,
    val maxTemp: String
) : Parcelable