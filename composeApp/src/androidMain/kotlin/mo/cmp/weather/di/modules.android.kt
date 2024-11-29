package mo.cmp.weather.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import mo.cmp.weather.api.WeatherAPI
import mo.cmp.weather.store.WeatherStore
import mo.cmp.weather.store.WeatherStoreExecutorFactory
import mo.cmp.weather.ui.LandingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


actual val platformModules: Module = module {
    single<StoreFactory> {
        DefaultStoreFactory()
    }
    single {
        WeatherAPI(get())
    }
    single { WeatherStoreExecutorFactory(get()) }
    single {
        WeatherStore(storeFactory = get(), weatherStoreExecutorFactory = get())
    }
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    viewModel {
        LandingViewModel(get<WeatherStore>())
    }
}
