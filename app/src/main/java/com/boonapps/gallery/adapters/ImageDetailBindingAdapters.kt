package com.boonapps.gallery.adapters

import android.os.Handler
import android.widget.HorizontalScrollView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}

@BindingAdapter("scrollCenter")
fun scrollCenter(view: HorizontalScrollView, isCenter: Boolean?) {
    if(isCenter != null && isCenter) {
        Handler().postDelayed( {
            val offsetX = view.width/2
            view.smoothScrollTo(offsetX, 0)
        }, 4000)
    }
}