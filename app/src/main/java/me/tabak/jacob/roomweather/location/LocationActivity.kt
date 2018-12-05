package me.tabak.jacob.roomweather.location

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import me.tabak.jacob.roomweather.R
import me.tabak.jacob.roomweather.databinding.ActivityLocationBinding
import javax.inject.Inject

class LocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocationBinding
    @Inject lateinit var presenter: LocationActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)
        binding.presenter = presenter
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
