package com.example.hw_3_5_month

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "33924781-83434e6876bb5371b62d7afbe",
        @Query("q") pictureWord: String,
        @Query("per_page") perPage: Int = 5,
        @Query("page") page: Int
    ): Call<PixaModel>
}
