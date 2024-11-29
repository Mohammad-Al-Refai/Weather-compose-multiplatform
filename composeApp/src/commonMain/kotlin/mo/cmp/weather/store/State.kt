package mo.cmp.weather.store

data class State(
    var searchValue: String = "",
    var temp: Double = 0.0,
    var countryName: String = "",
    var feelsLike: Double = 0.0,
    var windSpeed: Double = 0.0,
    var isLoading: Boolean = false,
    var isError: Boolean = false,
    var isSuccess: Boolean = false
)