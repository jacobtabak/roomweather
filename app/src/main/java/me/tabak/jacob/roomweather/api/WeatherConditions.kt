package me.tabak.jacob.roomweather.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Contains general weather conditions (like cloudy, snow, sunny, etc) and an icon
 */
@Parcelize
class WeatherConditions(
    @SerializedName("main") val condition: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
) : Parcelable
