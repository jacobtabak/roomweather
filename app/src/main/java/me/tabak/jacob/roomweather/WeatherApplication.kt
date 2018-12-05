package me.tabak.jacob.roomweather

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class WeatherApplication : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>
    private lateinit var component: WeatherApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerWeatherApplicationComponent.builder()
            .weatherApplicationModule(WeatherApplicationModule(this))
            .build().apply { inject(this@WeatherApplication) }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}