package me.tabak.jacob.roomweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherLocation(
    @PrimaryKey val id: Int,
    val name: String,
    val zipCode: Int
)