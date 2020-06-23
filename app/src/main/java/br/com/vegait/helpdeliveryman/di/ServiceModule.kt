package br.com.vegait.helpdeliveryman.di

import br.com.vegait.helpdeliveryman.data.api.RetrofitFactory
import br.com.vegait.helpdeliveryman.data.api.WeatherApi
import org.koin.dsl.module
import retrofit2.create


val serviceModule = module {

    single {
        RetrofitFactory.provideRetrofit(get()).create<WeatherApi>()
    }
}
