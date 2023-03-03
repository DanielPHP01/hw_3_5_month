package com.example.hw_3_5_month.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hw_3_5_month.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PixaAdapter
    private val viewModel: PixaViewModel by lazy {
        ViewModelProvider(this)[PixaViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PixaAdapter()
        binding.recyclerView.adapter = adapter
        binding.btnFood.setOnClickListener {
            val food = "food"
            viewModel.getSearchPaging(food).observe(this, Observer {
                adapter.submitData(lifecycle, it)
            })
        }

        binding.btnEnter.setOnClickListener {
            val searchQuery = binding.etSearch.text.toString().trim()
            if (searchQuery.isNotEmpty()) {
                viewModel.getSearchPaging(searchQuery).observe(this, Observer {
                    adapter.submitData(lifecycle, it)
                })
            }
        }
    }
}