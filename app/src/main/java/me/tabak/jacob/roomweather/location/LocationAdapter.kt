package me.tabak.jacob.roomweather.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import me.tabak.jacob.roomweather.databinding.ItemLocationBinding
import me.tabak.jacob.roomweather.model.WeatherLocation
import me.tabak.jacob.roomweather.util.DataBoundViewHolder

class LocationAdapter :
    RecyclerView.Adapter<DataBoundViewHolder<ItemLocationBinding>>(),
    Observer<List<WeatherLocation>> {

    private var locations: List<WeatherLocation> = emptyList()

    override fun onChanged(locations: List<WeatherLocation>) {
        this.locations = locations
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
        holder.binding.location = locations[position]
    }
}