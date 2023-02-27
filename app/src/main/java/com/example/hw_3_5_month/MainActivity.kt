package com.example.hw_3_5_month

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3_5_month.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PixaAdapter
    private lateinit var viewModel: PixaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PixaAdapter(arrayListOf())
        viewModel = ViewModelProvider(this).get(PixaViewModel::class.java)

        binding.recyclerView.adapter = adapter

        binding.btnEnter.setOnClickListener {
            val searchQuery = binding.etSearch.text.toString().trim()
            if (searchQuery.isNotEmpty()) {
                viewModel.searchImages(searchQuery)
            }
        }

    }
}