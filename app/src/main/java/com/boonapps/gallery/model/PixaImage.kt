package com.boonapps.gallery.model

import android.os.Parcelable
import android.text.TextUtils
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PixaImage(
        val id: Long,
        private val likes: Int,
        private val favorites: Int,
        private val tags: String?,
        private val comments: Int,
        private val user: String,
        val pageURL: String,
        val previewURL: String,
        val largeImageURL: String
) : Parcelable {
    fun getTags(): String {
        if (tags == null) return ""
        return if (tags.contains(", ")) {
            val splitTags = tags.toUpperCase().split(", ").toTypedArray()
            TextUtils.join(" - ", splitTags)
        } else tags
    }

    fun getComments() =  comments.toString()

    fun getFavorites() = favorites.toString()

    fun getLikes() = likes.toString()

    fun getUser() =  "By: $user"
}