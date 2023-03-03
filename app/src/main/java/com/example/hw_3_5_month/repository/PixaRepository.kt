package com.example.hw_3_5_month.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.hw_3_5_month.Hit
import com.example.hw_3_5_month.remote.PixaService
import com.example.hw_3_5_month.remote.pagingsource.PixaPagingSource

class PixaRepository() {
    private val api = PixaService.api

    fun searchImages(searchQuery: String): LiveData<PagingData<Hit>> {
        val pagingSourceFactory = { PixaPagingSource(api, searchQuery) }
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = 100
            ),
            pagingSourceFactory = pagingSourceFactory
        ).liveData
    }
}