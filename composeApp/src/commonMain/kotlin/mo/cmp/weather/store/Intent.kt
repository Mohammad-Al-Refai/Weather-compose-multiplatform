package mo.cmp.weather.store

sealed class Intent {
    data class UpdateSearch(var searchValue: String) : Intent()
    data object Search : Intent()
}