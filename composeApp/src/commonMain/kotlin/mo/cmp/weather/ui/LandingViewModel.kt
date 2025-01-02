package mo.cmp.weather.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import mo.cmp.weather.api.WeatherAPI
import mo.cmp.weather.api.WeatherAPIStatus
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

sealed class LandingSideEffect {
    data class ErrorSnackBar(val text: String) : LandingSideEffect()
    data class SuccessSnackBar(val text: String) : LandingSideEffect()
}

data class LandingState(
    var searchValue: String = "Syria",
    var temp: Double = 0.0,
    var countryName: String = "",
    var feelsLike: Double = 0.0,
    var windSpeed: Double = 0.0,
    var isLoading: Boolean = false,
    var isError: Boolean = false,
    var isSuccess: Boolean = false
)

class LandingViewModel : ScreenModel,
    ContainerHost<LandingState, LandingSideEffect>, KoinComponent {
    private val weatherAPI by inject<WeatherAPI>()
    override val container =
        screenModelScope.container<LandingState, LandingSideEffect>(LandingState())

    init {
        search()
    }

    fun updateSearch(value: String) = intent {
        reduce {
            state.copy(searchValue = value)
        }
    }

    fun search() = intent {
        reduce {
            state.copy(isLoading = true, isError = false, isSuccess = false)
        }
        when (val result = weatherAPI.getDataBySearch(searchValue = state.searchValue)) {
            is WeatherAPIStatus.Error -> {
                reduce {
                    state.copy(isLoading = false, isError = true, isSuccess = false)
                }
                postSideEffect(LandingSideEffect.ErrorSnackBar("Ops unexpected error accord!"))
            }

            is WeatherAPIStatus.Success -> {
                reduce {
                    state.copy(
                        isLoading = false,
                        isSuccess = true,
                        windSpeed = result.data.wind.speed,
                        feelsLike = result.data.main.feelsLike,
                        countryName = result.data.sys.country,
                        temp = result.data.main.temp
                    )
                }
                postSideEffect(LandingSideEffect.SuccessSnackBar("Request Succeeded!"))
            }
        }

    }
}