package com.example.hw_3_5_month

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PixaService {
    private const val BASE_URL = "https://pixabay.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: PixaApi = retrofit.create(PixaApi::class.java)
}