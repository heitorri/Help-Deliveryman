package br.com.vegait.helpdeliveryman.data.api

import br.com.vegait.helpdeliveryman.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast/daily")
    suspend fun getWeatherByCityName(
        @Query("city") string: String,
        @Query("days") daysCount: Int,
        @Query("lang") lang: String,
        @Query("country") country: String,
        @Query("key") apiKey: String
    ): Response<WeatherResponse>
}