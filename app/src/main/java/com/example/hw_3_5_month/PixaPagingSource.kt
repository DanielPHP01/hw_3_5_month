package com.example.hw_3_5_month

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PixaPagingSource(
    private val apiService: PixaApi,
    private val query: String
) : PagingSource<Int, Hit>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        val page = params.key ?: STARTING_PAGE_INDEX
        val pageSize = params.loadSize.coerceAtMost(PAGE_SIZE)

        return try {
            val response = apiService.getImages(pictureWord = query, page = page, perPage = pageSize)
            val hits = response.hits

            LoadResult.Page(
                data = hits,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (hits.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        } ?: STARTING_PAGE_INDEX
    }
}
