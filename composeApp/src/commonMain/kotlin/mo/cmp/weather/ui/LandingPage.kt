package mo.cmp.weather.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mo.cmp.weather.store.Intent


@Composable
fun LandingPage(viewModel: LandingViewModel) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = state.searchValue,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    onValueChange = {
                        viewModel.onIntent(intent = Intent.UpdateSearch(it))
                    })
                Button(onClick = {
                    viewModel.onIntent(Intent.Search)
                }) {
                    Text("Search")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            if (state.isError) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .fillMaxHeight(0.1f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colors.error),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Unexpected error", color = MaterialTheme.colors.onError)
                }
            }
            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.isSuccess) {
                PageContent(
                    countryName = state.countryName,
                    temp = state.temp,
                    feelsLike = state.feelsLike,
                    windSpeed = state.windSpeed
                )
            }

        }
    }
}
@Composable
fun PageContent(countryName: String, temp: Double, feelsLike: Double, windSpeed: Double) {
    val visible  = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible.value = !visible.value
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(visible = visible.value, enter = fadeIn()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundedBox(
                    title = "Country",
                    content = countryName,
                    color = MaterialTheme.colors.primary
                )
                Spacer(Modifier.padding(5.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RoundedBox(
                        title = "Temperature",
                        content = "${temp}C",
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.padding(5.dp))
                    RoundedBox(
                        title = "Feels like",
                        content = "${feelsLike}C",
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(Modifier.padding(5.dp))
                RoundedBox(
                    title = "Wind speed",
                    content = windSpeed.toString(),
                    color = MaterialTheme.colors.secondaryVariant,
                )
            }
        }
    }
}

@Composable
fun RoundedBox(title: String, content: String, color: Color, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = color)
    ) {
        Text(
            title,
            modifier = Modifier.padding(10.dp),
            color = MaterialTheme.colors.onPrimary
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                content,
                fontSize = MaterialTheme.typography.h3.fontSize,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

