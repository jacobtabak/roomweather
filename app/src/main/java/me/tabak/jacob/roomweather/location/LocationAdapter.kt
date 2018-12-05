package me.tabak.jacob.roomweather.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import me.tabak.jacob.roomweather.databinding.ItemLocationBinding
import me.tabak.jacob.roomweather.detail.DetailActivity
import me.tabak.jacob.roomweather.entity.WeatherLocation
import me.tabak.jacob.roomweather.util.DataBoundViewHolder

/**
 * Adapter that displays a list of locations that the user has added
 */
class LocationAdapter :
    RecyclerView.Adapter<DataBoundViewHolder<ItemLocationBinding>>(),
    Observer<List<WeatherLocation>> {

    private var locations: List<WeatherLocation> = emptyList()
    val isEmpty: ObservableBoolean = ObservableBoolean()

    override fun onChanged(locations: List<WeatherLocation>) {
        this.locations = locations
        isEmpty.set(locations.isEmpty())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBoundViewHolder<ItemLocationBinding> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLocationBinding.inflate(inflater, parent, false)
        return DataBoundViewHolder(binding)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: DataBoundViewHolder<ItemLocationBinding>, position: Int) {
        val location = locations[position]
        holder.binding.location = location
        val view = holder.binding.root
        val context = view.context
        val intent = DetailActivity.newIntent(context, location)
        view.setOnClickListener { context.startActivity(intent) }
    }
}