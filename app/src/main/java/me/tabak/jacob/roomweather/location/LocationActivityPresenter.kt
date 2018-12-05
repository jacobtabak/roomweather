package me.tabak.jacob.roomweather.location

import androidx.recyclerview.widget.LinearLayoutManager
import me.tabak.jacob.roomweather.add.AddActivity
import me.tabak.jacob.roomweather.data.WeatherDatabase
import javax.inject.Inject

/**
 * Contains UI logic for the location selection activity.
 * - Sets up the adapter to observe the database
 * - Handles the FAB button clicks
 */
class LocationActivityPresenter @Inject constructor(
    private val activity: LocationActivity,
    private val database: WeatherDatabase
) {
    val adapter = LocationAdapter()
    val layoutManager = LinearLayoutManager(activity)

    /**
     * Observe the weather database for changes while this activity is active
     */
    fun onStart() {
        database.weatherLocationDao().getAll().observe(activity, adapter)
    }

    /**
     * Event handler for when the user clicks the "add" floating action button
     */
    fun addClicked() {
        activity.startActivity(AddActivity.newIntent(activity))
    }
}