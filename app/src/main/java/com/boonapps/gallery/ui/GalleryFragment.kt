package com.boonapps.gallery.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.boonapps.gallery.R
import com.boonapps.gallery.adapters.GalleryAdapter
import com.boonapps.gallery.api.PixaApiService
import com.boonapps.gallery.model.PixaRepository
import com.boonapps.gallery.databinding.FragmentGalleryBinding
import com.boonapps.gallery.viewmodels.GalleryViewModel
import com.boonapps.gallery.viewmodels.GalleryViewModelFactory
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class GalleryFragment : Fragment() {

    private val adapter = GalleryAdapter()
    private var searchJob: Job? = null
    private val viewModel: GalleryViewModel by viewModels {
        provideGalleryViewModelFactory()
    }

     var lastQuery : String = "bus"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        retainInstance = true
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGalleryBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.photoList.adapter = adapter
        setHasOptionsMenu(true)

        search(lastQuery)
        return binding.root
    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchPictures(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_gallery, menu)
        val myActionMenuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.getActionView() as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                lastQuery = query
                search(query)
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                return false
            }
        })
    }

}

inline fun provideGalleryViewModelFactory(): GalleryViewModelFactory {
    val repository = PixaRepository(PixaApiService.create())
    return GalleryViewModelFactory(repository)
}
