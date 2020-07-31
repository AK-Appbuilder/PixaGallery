package com.boonapps.gallery.model

import androidx.paging.PagingSource
import com.boonapps.gallery.api.PixaApiService

private const val STARTING_PAGE_INDEX = 1

class PixaPagingSource (
        private val service: PixaApiService,
        private val query: String
) : PagingSource<Int, PixaImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixaImage> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.searchImages(query, page, params.loadSize)
            val images = response.hits
            LoadResult.Page(
                data = images,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalOfPages) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}
