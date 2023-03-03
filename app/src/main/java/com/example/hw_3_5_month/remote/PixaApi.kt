package com.example.hw_3_5_month.remote

import com.example.hw_3_5_month.PixaModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String = "33924781-83434e6876bb5371b62d7afbe",
        @Query("q") pictureWord: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): PixaModel
}


