package me.tabak.jacob.roomweather.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.tabak.jacob.roomweather.entity.WeatherLocation

@Database(entities = [WeatherLocation::class], version = 3)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherLocationDao(): WeatherLocationDao
}