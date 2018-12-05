package me.tabak.jacob.roomweather.location

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import me.tabak.jacob.roomweather.R
import me.tabak.jacob.roomweather.databinding.ActivityLocationBinding
import javax.inject.Inject

/**
 * Activity that displays the user's list of locations that they have added.
 * The UI is managed by [LocationActivityPresenter] via databinding.
 */
class LocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationBinding
    @Inject lateinit var presenter: LocationActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)
        binding.presenter = presenter
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    @Subcomponent
    interface LocationActivitySubcomponent : AndroidInjector<LocationActivity> {
        @Subcomponent.Builder
        abstract class Builder : AndroidInjector.Builder<LocationActivity>()
    }

    @Module(subcomponents = [LocationActivitySubcomponent::class])
    abstract class LocationActivityModule {
        @ContributesAndroidInjector abstract fun contributeInjector(): LocationActivity
    }
}
