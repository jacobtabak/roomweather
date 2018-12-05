package me.tabak.jacob.roomweather.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.Module
import dagger.Subcomponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import me.tabak.jacob.roomweather.R
import me.tabak.jacob.roomweather.databinding.ActivityAddBinding
import javax.inject.Inject

class AddActivity : AppCompatActivity() {
    internal lateinit var binding: ActivityAddBinding
    @Inject lateinit var presenter: AddActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        binding.presenter = presenter
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @Subcomponent
    interface AddActivitySubcomponent : AndroidInjector<AddActivity> {
        @Subcomponent.Builder
        abstract class Builder : AndroidInjector.Builder<AddActivity>()
    }

    @Module(subcomponents = [AddActivitySubcomponent::class])
    abstract class AddActivityModule {
        @ContributesAndroidInjector
        abstract fun contributeInjector(): AddActivity
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, AddActivity::class.java)
    }
}