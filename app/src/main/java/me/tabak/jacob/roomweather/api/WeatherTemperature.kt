package me.tabak.jacob.roomweather.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherTemperature(
    @SerializedName("temp") val temperature: Float,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_min") val minTemperature: Float,
    @SerializedName("temp_max") val maxTemperature: Float
) : Parcelable
