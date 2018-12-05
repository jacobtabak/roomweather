package me.tabak.jacob.roomweather.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?units=imperial")
    fun getWeather(@Query("zip") zipCode: String): Call<WeatherResponse>
}