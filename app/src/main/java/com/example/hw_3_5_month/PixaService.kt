package com.example.hw_3_5_month

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixaService {

    private var retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val api = retrofit.create(PixaApi::class.java)
}