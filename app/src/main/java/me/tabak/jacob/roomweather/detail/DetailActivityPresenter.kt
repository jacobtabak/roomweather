package me.tabak.jacob.roomweather.detail

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import me.tabak.jacob.roomweather.api.WeatherApi
import me.tabak.jacob.roomweather.api.WeatherResponse
import me.tabak.jacob.roomweather.data.WeatherDatabase
import me.tabak.jacob.roomweather.model.WeatherLocation
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailActivityPresenter @Inject constructor(
    private val activity: DetailActivity,
    private val api: WeatherApi,
    private val database: WeatherDatabase
) : BaseObservable(), Callback<WeatherResponse> {
    var location: WeatherLocation = DetailActivity.getLocation(activity.intent)
    var isError = ObservableBoolean(false)
    var isLoading = ObservableBoolean(true)

    var response: WeatherResponse? = null
        set(value) {
            field = value
            notifyChange()
        }

    fun onStart() {
        loadData()
    }

    fun loadData() {
        isLoading.set(true)
        isError.set(false)
        api
            .getWeather(location.zipCode + ZIP_SUFFIX)
            .enqueue(this)
    }

    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
        Log.e("Weather", "Unable to load weather", t)
        isLoading.set(false)
        isError.set(true)
    }

    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
        isLoading.set(false)
        if (response.isSuccessful) {
            this.response = response.body()
        } else {
            isError.set(true)
        }
    }

    fun deleteLocation() {
        doAsync {
            database.weatherLocationDao().delete(location)
            activity.finish()
        }
    }

    fun iconUrl() = ICON_PREFIX + response?.conditions?.firstOrNull()?.icon + ICON_SUFFIX

    fun title() = location.name

    fun city() = response?.name

    fun currentTemp() = FLOAT_FORMAT.format(response?.temperature?.temperature)

    fun highTemp() = FLOAT_FORMAT.format(response?.temperature?.maxTemperature)

    fun lowTemp() = FLOAT_FORMAT.format(response?.temperature?.minTemperature)

    fun condition() = response?.conditions?.firstOrNull()?.condition

    companion object {
        private const val FLOAT_FORMAT = "%.1f" // show the first decimal of a float
        private const val ICON_PREFIX = "https://openweathermap.org/img/w/"
        private const val ICON_SUFFIX = ".png"
        private const val ZIP_SUFFIX = ",us" // zip code needs a country code suffix
    }
}