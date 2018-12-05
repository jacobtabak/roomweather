package me.tabak.jacob.roomweather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.tabak.jacob.roomweather.model.WeatherLocation

@Database(entities = [WeatherLocation::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherLocationDao(): WeatherLocationDao
}