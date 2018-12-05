package me.tabak.jacob.roomweather.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.tabak.jacob.roomweather.model.WeatherLocation

@Dao
interface WeatherLocationDao {
    @Query("SELECT * FROM WeatherLocation")
    fun getAll(): LiveData<List<WeatherLocation>>

    @Query("SELECT * FROM WeatherLocation WHERE id = :id")
    fun get(id: Int): LiveData<WeatherLocation>

    @Query("SELECT COUNT(*) FROM WeatherLocation")
    fun getCount(): LiveData<Int>

    @Insert
    fun insert(weatherLocation: WeatherLocation)
}