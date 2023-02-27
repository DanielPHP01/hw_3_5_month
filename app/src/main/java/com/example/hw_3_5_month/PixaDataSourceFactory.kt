package com.example.hw_3_5_month

import androidx.paging.DataSource

class PixaDataSourceFactory(private val searchWord: String) : DataSource.Factory<Int, Hit>() {
    override fun create(): DataSource<Int, Hit> {
        return PixaDataSource(searchWord)
    }
}