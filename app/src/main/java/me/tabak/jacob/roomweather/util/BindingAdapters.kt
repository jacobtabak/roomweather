package me.tabak.jacob.roomweather.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {
    @JvmStatic @BindingAdapter(value = ["visible"])
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic @BindingAdapter(value = ["imageUrl"])
    fun loadImage(view: ImageView, url: String) {
        Picasso.get().load(url).into(view)
    }
}