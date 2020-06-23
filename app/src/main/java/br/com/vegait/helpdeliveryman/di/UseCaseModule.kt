package br.com.vegait.helpdeliveryman.di

import br.com.vegait.helpdeliveryman.domain.usecase.LoadWeaterByNameUseCase
import br.com.vegait.helpdeliveryman.domain.usecase.LoadWeaterByNameUseCaseImpl
import org.koin.dsl.module


val moduleUseCase = module {
    single<LoadWeaterByNameUseCase> {
        LoadWeaterByNameUseCaseImpl(
            get()
        )
    }
}
