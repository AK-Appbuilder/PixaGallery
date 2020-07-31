package com.boonapps.gallery.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.boonapps.gallery.R
import com.boonapps.gallery.databinding.ActivityGalleryBinding

class PixaGalleyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityGalleryBinding>(this, R.layout.activity_gallery)
    }
}