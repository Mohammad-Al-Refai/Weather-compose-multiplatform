package mo.cmp.weather

import android.app.Application
import mo.cmp.weather.di.platformModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(platformModules)
        }
    }
}