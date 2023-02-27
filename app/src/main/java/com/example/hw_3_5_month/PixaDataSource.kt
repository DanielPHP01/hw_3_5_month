package com.example.hw_3_5_month

import android.util.Log
import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PixaDataSource(private val searchWord: String) : PageKeyedDataSource<Int, Hit>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Hit>
    ) {
        // Load the first page of data from the API
        PixaService().api.getImages(pictureWord = searchWord, page = 1)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.hits ?: emptyList()
                        callback.onResult(data, null, 2)
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("PixaDataSource", "Failed to load initial data", t)
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Hit>) {
        // Load the next page of data from the API
        PixaService().api.getImages(pictureWord = searchWord, page = params.key)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.hits ?: emptyList()
                        callback.onResult(data, params.key + 1)
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("PixaDataSource", "Failed to load page ${params.key}", t)
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Hit>) {
        // Not needed in this case, since we're only paging forward
    }
}
