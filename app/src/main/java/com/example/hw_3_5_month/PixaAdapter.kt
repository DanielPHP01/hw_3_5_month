package com.example.hw_3_5_month

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.hw_3_5_month.databinding.ItemAndroidBinding

class PixaAdapter(var list: ArrayList<Hit>) : Adapter<PixaAdapter.PixaViewHolder>() {
    class PixaViewHolder(var binding: ItemAndroidBinding) : ViewHolder(binding.root) {
        fun onBind(hit: Hit) {
            binding.imgRv.load(hit.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemAndroidBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}