package br.com.vegait.helpdeliveryman.domain.usecase

import br.com.vegait.helpdeliveryman.BuildConfig
import br.com.vegait.helpdeliveryman.data.api.WeatherApi
import br.com.vegait.helpdeliveryman.data.model.WeatherDetail
import br.com.vegait.helpdeliveryman.domain.exception.NoContentException
import br.com.vegait.helpdeliveryman.util.getFormattedDateDayMonth

class LoadWeaterByNameUseCaseImpl(
    private val api: WeatherApi
) : LoadWeaterByNameUseCase {

    override suspend fun loadWeatherBycity(cityName: String): WeatherDetail {
        val response = api.getWeatherByCityName(
            cityName,
            2,
            "pt",
            "BR",
            BuildConfig.API_KEY
        )
        if (response.code() == 204) {
            throw NoContentException()
        }
        val dataItem = response.body()!!.data.last()

        return WeatherDetail(
            minTemp = dataItem.minTemp,
            maxTemp = dataItem.maxTemp,
            description = dataItem.weather.description,
            icon = dataItem.weather.icon,
            dateSearch = dataItem.date.getFormattedDateDayMonth()
        )
    }
}