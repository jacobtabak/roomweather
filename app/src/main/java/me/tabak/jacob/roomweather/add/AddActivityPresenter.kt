package me.tabak.jacob.roomweather.add

import android.widget.TextView
import me.tabak.jacob.roomweather.R
import me.tabak.jacob.roomweather.data.WeatherDatabase
import me.tabak.jacob.roomweather.entity.WeatherLocation
import org.jetbrains.anko.doAsync
import javax.inject.Inject

/**
 * Manages the UI for [AddActivity] via databinding
 */
class AddActivityPresenter @Inject constructor(
    private val activity: AddActivity,
    private val database: WeatherDatabase
) {
    fun addClicked() {
        val binding = activity.binding
        val nameText = binding.name.text.toString()
        val zipCodeText = binding.zipCode.text.toString()

        var error = false
        if (nameText.isBlank()) {
            binding.nameInputLayout.error = activity.getString(R.string.name_empty)
            error = true
        }

        if (zipCodeText.toIntOrNull() == null) {
            binding.zipCodeInputLayout.error = activity.getString(R.string.zip_numeric)
            error = true
        }

        if (zipCodeText.length != 5) {
            binding.zipCodeInputLayout.error = activity.getString(R.string.zip_five_digits)
            error = true
        }

        if (!error) {
            val weatherLocation = WeatherLocation(name = nameText, zipCode = zipCodeText)
            doAsync {
                database.weatherLocationDao().insert(weatherLocation)
                activity.finish()
            }
        }
    }

    fun actionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, _, _ ->
            addClicked()
            true
        }
    }
}