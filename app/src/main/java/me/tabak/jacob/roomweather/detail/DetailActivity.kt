package me.tabak.jacob.roomweather.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import me.tabak.jacob.roomweather.R
import me.tabak.jacob.roomweather.databinding.ActivityDetailBinding
import me.tabak.jacob.roomweather.model.WeatherLocation
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    @Inject lateinit var presenter: DetailActivityPresenter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.presenter = presenter
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.delete) {
            presenter.deleteLocation()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    @Subcomponent
    interface DetailActivitySubcomponent : AndroidInjector<DetailActivity> {
        @Subcomponent.Builder
        abstract class Builder: AndroidInjector.Builder<DetailActivity>()
    }

    @Module(subcomponents = [DetailActivitySubcomponent::class])
    abstract class DetailActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeInjector(): DetailActivity
    }

    companion object {
        private const val KEY_LOCATION = "location"

        fun newIntent(context: Context, location: WeatherLocation): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(KEY_LOCATION, location)
            }
        }

        fun getLocation(intent: Intent): WeatherLocation {
            return intent.getParcelableExtra(KEY_LOCATION)
        }
    }
}