package me.tabak.jacob.roomweather.location

import me.tabak.jacob.roomweather.data.WeatherDatabase
import javax.inject.Inject

class LocationActivityPresenter @Inject constructor(
    activity: LocationActivity,
    database: WeatherDatabase
) {
    private val adapter = LocationAdapter()

    init {
        database.weatherLocationDao().getAll().observe(activity, adapter)
    }
}