package com.example.sriram.Model


data class DataModel(
    val cod: Int,
    val message: Int,
    val cnt: String,
    val list: List<ItemList?>? = null,
    val city: City? = null
)

data class DetailsModel(
    val temperature: String? = null,
    val feelsLikeText: String? = null,
    val weatherType: String? = null,
    val weatherTypeDesc: String? = null,
)

data class City(
    val id: Int,
    val name: String? = null,
    val country: String? = null,
    val coord: Coord? = null,
    val population: Int,
    val timeZone: Int,
    val sunrisae: Int,
    val sunset: Int

)

data class Coord(
    val lat: Float,
    val lon: Float
)

data class ItemList(
    val dt: Int,
    val main: Main? = null,
    val weather: List<Weather>? = null,
    val clouds: Clouds? = null,
    val wind: Wind? = null,
    val sys: Sys? = null,
    val visibility: Int,
    val dt_txt: String? = null
)

data class Main(
    val temp: String? = null,
    val feels_like: String? = null,
    val temp_min: String? = null,
    val temp_max: String? = null,
    val pressure: String? = null,
    val sea_level: String? = null,
    val grnd_level: String? = null,
    val humidity: String? = null,
    val temp_kf: String? = null
)

data class Weather(
    val id: Int,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)

data class Clouds(
    val all: Int

)

data class Wind(
    val speed: Float? = null,
    val deg: Int,
    val gust: Float?

)

data class Sys(
    val pod: String? = null
)
