package me.tabak.jacob.roomweather.location

import androidx.recyclerview.widget.LinearLayoutManager
import me.tabak.jacob.roomweather.add.AddActivity
import me.tabak.jacob.roomweather.data.WeatherDatabase
import javax.inject.Inject

class LocationActivityPresenter @Inject constructor(
    private val activity: LocationActivity,
    private val database: WeatherDatabase
) {
    val adapter = LocationAdapter()
    val layoutManager = LinearLayoutManager(activity)

    fun addClicked() {
        activity.startActivity(AddActivity.newIntent(activity))
    }

    fun onStart() {
        database.weatherLocationDao().getAll().observe(activity, adapter)
    }
}