package me.tabak.jacob.roomweather.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize @Entity
data class WeatherLocation(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val zipCode: String
) : Parcelable