package me.tabak.jacob.roomweather

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.tabak.jacob.roomweather.data.WeatherDatabase

@Module
class WeatherApplicationModule(val application: Application) {
    @Provides fun provideWeatherDatabase(): WeatherDatabase = Room.databaseBuilder(
        application,
        WeatherDatabase::class.java,
        DATABASE_NAME
    ).build()

    companion object {
        private const val DATABASE_NAME: String = "weather_database"
    }
}