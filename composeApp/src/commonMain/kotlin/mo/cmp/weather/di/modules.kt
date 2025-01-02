package mo.cmp.weather.di

import mo.cmp.weather.api.WeatherAPI
import mo.cmp.weather.ui.LandingViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect val platformModules: Module

val commonModules = module {
    single { WeatherAPI(get()) }
    single { LandingViewModel() }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(platformModules,commonModules)
    }
}