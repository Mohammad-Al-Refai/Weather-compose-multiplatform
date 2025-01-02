package mo.cmp.weather

import android.app.Application
import mo.cmp.weather.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApp:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@MainApp)
        }
    }
}