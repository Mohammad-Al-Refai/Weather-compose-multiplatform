package mo.cmp.weather.store

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import mo.cmp.weather.api.WeatherAPI
import mo.cmp.weather.api.WeatherAPIStatus

class WeatherStoreExecutorFactory(private var weatherAPI: WeatherAPI) :
    CoroutineExecutor<Intent, Nothing, State, Msg, Nothing>() {

    override fun executeIntent(intent: Intent) =
        when (intent) {
            Intent.Search -> getWeather(state().searchValue)
            is Intent.UpdateSearch -> {
                dispatch(Msg.UpdateState(state = state().copy(searchValue = intent.searchValue)))
            }
        }

    private fun getWeather(searchValue: String) {
        dispatch(
            Msg.UpdateState(
                state().copy(
                    isLoading = true,
                    isSuccess = false,
                    isError = false
                )
            )
        )
        scope.launch {
            when (val result = weatherAPI.getDataBySearch(searchValue)) {
                is WeatherAPIStatus.Error -> {
                    dispatch(
                        Msg.UpdateState(
                            state().copy(
                                isError = true,
                                isLoading = false,
                                isSuccess = false
                            )
                        )
                    )
                }
                is WeatherAPIStatus.Success -> {
                    dispatch(
                        Msg.UpdateState(
                            state().copy(
                                isError = false,
                                isLoading = false,
                                isSuccess = true,
                                temp = result.data.main.temp,
                                windSpeed = result.data.wind.speed,
                                countryName = result.data.sys.country,
                                feelsLike = result.data.main.feelsLike
                            )
                        )
                    )
                }
            }
        }
    }
}