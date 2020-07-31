package com.boonapps.gallery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.boonapps.gallery.model.PixaRepository

class GalleryViewModelFactory (
    private val pixaRepository: PixaRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(
            pixaRepository
        ) as T
    }
}
