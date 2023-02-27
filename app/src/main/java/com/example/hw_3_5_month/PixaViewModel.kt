package com.example.hw_3_5_month

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData

class PixaViewModel : ViewModel() {
    private val apiService = PixaService.api
    private val searchQuery = MutableLiveData<String>()

    val searchResult = searchQuery.switchMap { query ->
        Pager(PagingConfig(pageSize = 20)) {
            PixaPagingSource(apiService, query)
        }.liveData.cachedIn(viewModelScope)
    }

    fun searchImages(query: String) {
        searchQuery.value = query
    }
}
