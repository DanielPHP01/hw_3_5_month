package com.example.hw_3_5_month

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_3_5_month.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var page = 1
    var adapter = PixaAdapter(arrayListOf())
    var images = arrayListOf<Hit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        ++page
                        addRequest()
                    }
                }
            })

            btnChange.setOnClickListener {
                ++page
                addRequest()
            }
            btnEnter.setOnClickListener {
                page = 1
                doRequest()
            }
        }
    }

    private fun ActivityMainBinding.doRequest() {
        PixaService().api.getImages(pictureWord = etSearch.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>,
                    response: Response<PixaModel>,
                ) {
                    if (response.isSuccessful) {
                        adapter = PixaAdapter(response.body()?.hits!!)
                        recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }
            })
    }

    fun ActivityMainBinding.addRequest() {
        PixaService().api.getImages(
            pictureWord = etSearch.text.toString(), page = page
        ).enqueue(object : Callback<PixaModel> {
            override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                if (response.isSuccessful) {
                    val newImages = response.body()?.hits!!
                    images.addAll(newImages)
                    adapter = PixaAdapter(images)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                Log.e("ololo", "onFailure: ${t.message}")
            }

        })
    }
}