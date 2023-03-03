package com.example.hw_3_5_month.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hw_3_5_month.Hit
import com.example.hw_3_5_month.remote.PixaApi

class PixaPagingSource(private val api: PixaApi, private val searchQuery: String) :
    PagingSource<Int, Hit>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult.Page<Int, Hit> {
        val page = params.key ?: 1
        val response = api.getImages( pictureWord = searchQuery, page = page, perPage = params.loadSize )
        val data = response.hits

        return LoadResult.Page(
            data,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (data.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {

        val anchorPosition = state.anchorPosition ?: return null

        // Get the page key associated with the anchor position
        val anchorPage = state.closestPageToPosition(anchorPosition)?.prevKey ?: 1

        // Always reload the first page after a refresh
        return anchorPage - 1
    }
}
