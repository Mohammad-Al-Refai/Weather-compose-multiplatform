package mo.cmp.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import mo.cmp.weather.ui.LandingViewModel
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val landingViewModel = koinViewModel<LandingViewModel>()
            App(landingViewModel)
        }
    }
}