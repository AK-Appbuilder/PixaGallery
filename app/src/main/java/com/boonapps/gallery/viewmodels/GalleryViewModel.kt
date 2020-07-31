package com.boonapps.gallery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.boonapps.gallery.model.PixaImage
import com.boonapps.gallery.model.PixaRepository
import kotlinx.coroutines.flow.Flow

class GalleryViewModel internal constructor(
    private val repository: PixaRepository
) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<PixaImage>>? = null

    fun searchPictures(queryString: String): Flow<PagingData<PixaImage>> {
        currentQueryValue = queryString
        val newResult: Flow<PagingData<PixaImage>> =
            repository.getSearchResultStream(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}
