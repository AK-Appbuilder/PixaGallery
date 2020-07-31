package com.boonapps.gallery.model

import com.boonapps.gallery.model.PixaRepository.Companion.NETWORK_PAGE_SIZE

class PixaApiResponse(val total: Int,
                      val totalHits: Int,
                      val hits: List<PixaImage>) {

    val totalOfPages: Int
        get() = Math.ceil(total / NETWORK_PAGE_SIZE.toDouble()).toInt()
}