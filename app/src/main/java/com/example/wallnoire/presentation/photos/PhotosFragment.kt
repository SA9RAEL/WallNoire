package com.example.wallnoire.presentation.photos

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallnoire.R
import com.example.wallnoire.base.extention.fragmentComponent
import com.example.wallnoire.databinding.FragmentPhotosBinding
import com.example.wallnoire.base.extention.observeNonNull
import com.example.wallnoire.base.view.BaseFragment
import com.example.wallnoire.presentation.photos.adapter.PhotosAdapter
import com.example.wallnoire.view.categoryview.Category
import com.example.wallnoire.view.categoryview.CategoryView

class PhotosFragment : BaseFragment<FragmentPhotosBinding, PhotosViewModel>(),
    CategoryView.CategoryClickListener {
    override val layoutResourceId: Int = R.layout.fragment_photos
    override val classTypeOfViewModel: Class<PhotosViewModel> = PhotosViewModel::class.java

    private val photosAdapter: PhotosAdapter by lazy { PhotosAdapter() }

    override fun init() {
        setupRecyclerView()
        setupCategoryView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.fragmentComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun setUpViewModelStateObservers() {
        viewModel.viewState.observeNonNull(viewLifecycleOwner) { viewState ->
            binding.viewState = viewState
        }
        runOnlyFirstInit { observePhotosData() }
    }

    private fun observePhotosData() {
        viewModel.getMovieList().observeNonNull(viewLifecycleOwner) {
            photosAdapter.submitList(it)
        }
    }

    override fun setupClickListeners() {
        photosAdapter.onPhotoItemClick = { photo ->
            findNavController().navigate(
                PhotosFragmentDirections.actionPhotosFragmentToPhotoDetailFragment(
                    photoId = photo.id
                )
            )
        }
        binding.imageButtonFavorite.setOnClickListener {
            findNavController().navigate(
                PhotosFragmentDirections.actionPhotosFragmentToFavoriteFragment()
            )
        }
    }

    private fun setupCategoryView() {
        CategoryView.Builder()
            .view(binding.categoryView)
            .categoryList(viewModel.categoryList)
            .listener(this@PhotosFragment)
            .startIndex(viewModel.selectedCategoryIndex)
            .build()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            (layoutManager as GridLayoutManager).spanSizeLookup = photosAdapter.spanSizeLookup
            adapter = photosAdapter
            itemAnimator = null
        }
    }

    override fun onCategoryClick(category: Category, index: Int) {
        viewModel.selectedCategoryIndex = index
        observePhotosData()
    }

    override fun onDestroy() {
        photosAdapter.onPhotoItemClick = null
        super.onDestroy()
    }
}