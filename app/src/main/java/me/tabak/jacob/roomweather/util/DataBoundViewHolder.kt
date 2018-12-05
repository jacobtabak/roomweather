package me.tabak.jacob.roomweather.util

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBoundViewHolder<T : ViewDataBinding> (val binding: T) :
    RecyclerView.ViewHolder(binding.root)
