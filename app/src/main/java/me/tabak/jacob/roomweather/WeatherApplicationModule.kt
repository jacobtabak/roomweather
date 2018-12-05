package me.tabak.jacob.roomweather

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.tabak.jacob.roomweather.api.OwmApiKeyInterceptor
import me.tabak.jacob.roomweather.api.WeatherApi
import me.tabak.jacob.roomweather.data.WeatherDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WeatherApplicationModule(val application: Application) {
    @Provides fun provideWeatherDatabase(): WeatherDatabase = Room
        .databaseBuilder(
            application,
            WeatherDatabase::class.java,
            DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()

    @Provides fun provideWeatherApi(client: OkHttpClient): WeatherApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(WEATHER_API_URL)
        .client(client)
        .build()
        .create(WeatherApi::class.java)

    @Provides fun provideClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val apiKeyInterceptor = OwmApiKeyInterceptor()
        return OkHttpClient
            .Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    companion object {
        private const val DATABASE_NAME = "weather_database"
        private const val WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/"
    }
}