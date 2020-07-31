package com.boonapps.gallery.model

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.boonapps.gallery.api.PixaApiService
import kotlinx.coroutines.flow.Flow

class PixaRepository (private val service: PixaApiService) {

    fun getSearchResultStream(query: String): Flow<PagingData<PixaImage>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { PixaPagingSource(service, query) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}
