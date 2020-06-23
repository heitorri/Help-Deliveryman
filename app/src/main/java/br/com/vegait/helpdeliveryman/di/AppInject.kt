package br.com.vegait.helpdeliveryman.di

import br.com.vegait.helpdeliveryman.data.api.RetrofitFactory.networkModule
import org.koin.core.module.Module


val allModules: List<Module> = listOf(
    networkModule,
    serviceModule,
    moduleUseCase,
    viewModelModule
)
