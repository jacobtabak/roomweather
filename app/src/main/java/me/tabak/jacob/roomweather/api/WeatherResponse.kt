package me.tabak.jacob.roomweather.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse(
    @SerializedName("weather") val conditions: List<WeatherConditions>,
    @SerializedName("main") val temperature: WeatherTemperature,
    @SerializedName("name") val name: String
) : Parcelable
