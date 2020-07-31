package com.boonapps.gallery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.boonapps.gallery.adapters.GalleryAdapter.GalleryViewHolder
import com.boonapps.gallery.model.PixaImage
import com.boonapps.gallery.databinding.ListItemImageBinding
import com.boonapps.gallery.ui.GalleryFragmentDirections

class GalleryAdapter : PagingDataAdapter<PixaImage, GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(ListItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class GalleryViewHolder(
        private val binding: ListItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.pixaImage?.let { image ->
                    navigateToImageDetail(image, view)
                }
            }
        }

        private fun navigateToImageDetail(pixaImage: PixaImage, view: View) {
            val direction = GalleryFragmentDirections
                    .actionGalleryFragmentToDetailFragment(pixaImage)
            view.findNavController().navigate(direction)
        }

        fun bind(item: PixaImage) {
            binding.apply {
                pixaImage = item
                executePendingBindings()
            }
        }
    }
}

private class GalleryDiffCallback : DiffUtil.ItemCallback<PixaImage>() {
    override fun areItemsTheSame(oldItem: PixaImage, newItem: PixaImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PixaImage, newItem: PixaImage): Boolean {
        return oldItem == newItem
    }
}
