package me.tabak.jacob.roomweather

import dagger.Component
import dagger.android.AndroidInjectionModule
import me.tabak.jacob.roomweather.add.AddActivity
import me.tabak.jacob.roomweather.detail.DetailActivity
import me.tabak.jacob.roomweather.location.LocationActivity

@Component(modules = [
    AndroidInjectionModule::class,
    WeatherApplicationModule::class,
    LocationActivity.LocationActivityModule::class,
    AddActivity.AddActivityModule::class,
    DetailActivity.DetailActivityModule::class
])
interface WeatherApplicationComponent {
    fun inject(weatherApplication: WeatherApplication)
}