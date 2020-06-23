package br.com.vegait.helpdeliveryman.di

import br.com.vegait.helpdeliveryman.module.searchCityWeather.viewmodel.SearchCityWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchCityWeatherViewModel(get()) }
}
