package com.example.hw_3_5_month.ui

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.hw_3_5_month.Hit
import com.example.hw_3_5_month.repository.PixaRepository

class PixaViewModel : ViewModel() {
   val repo = PixaRepository()

    fun getSearchPaging(search:String):LiveData<PagingData<Hit>>{
        return repo.searchImages(search)
    }
}
