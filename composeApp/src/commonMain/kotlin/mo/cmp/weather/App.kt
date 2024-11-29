package mo.cmp.weather

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import mo.cmp.weather.ui.LandingPage
import mo.cmp.weather.ui.LandingViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App(viewModel: LandingViewModel = koinViewModel<LandingViewModel>()) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            LandingPage(viewModel = viewModel)
        }
    }
}