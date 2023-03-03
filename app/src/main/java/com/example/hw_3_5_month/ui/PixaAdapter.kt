package com.example.hw_3_5_month.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hw_3_5_month.Hit
import com.example.hw_3_5_month.databinding.ItemAndroidBinding

class PixaAdapter : PagingDataAdapter<Hit, PixaAdapter.PixaViewHolder>(PIXACOMPARATOR) {
    inner class PixaViewHolder(private val binding: ItemAndroidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Hit?) {
item?.let {
    binding.imgRv.load(item.largeImageURL)
}

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemAndroidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

    }

    companion object {
        private val PIXACOMPARATOR = object : DiffUtil.ItemCallback<Hit>() {
            override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
                return oldItem.largeImageURL == newItem.largeImageURL
            }

            override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
                return oldItem == newItem
            }
        }
    }
}