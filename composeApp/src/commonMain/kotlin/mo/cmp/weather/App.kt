package mo.cmp.weather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import mo.cmp.weather.ui.LandingPage
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                Navigator(LandingPage()){
                    SlideTransition(it)
                }
            }
        }
}

