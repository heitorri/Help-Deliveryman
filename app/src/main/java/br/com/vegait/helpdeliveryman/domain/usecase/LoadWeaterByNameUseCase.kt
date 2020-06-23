package br.com.vegait.helpdeliveryman.domain.usecase

import br.com.vegait.helpdeliveryman.data.model.WeatherDetail

interface LoadWeaterByNameUseCase {

    suspend fun loadWeatherBycity(cityName: String): WeatherDetail
}