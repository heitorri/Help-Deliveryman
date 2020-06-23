package br.com.vegait.helpdeliveryman.app

import android.app.Application
import br.com.vegait.helpdeliveryman.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(allModules)
        }
    }
}