package com.example.wallnoire.presentation.favorite

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.wallnoire.R
import com.example.wallnoire.base.extention.fragmentComponent
import com.example.wallnoire.databinding.FragmentFavoriteBinding
import com.example.wallnoire.base.extention.observeNonNull
import com.example.wallnoire.base.view.BaseFragment
import com.example.wallnoire.presentation.favorite.adapter.FavoriteAdapter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_favorite
    override val classTypeOfViewModel: Class<FavoriteViewModel> = FavoriteViewModel::class.java

    private val adapter: FavoriteAdapter by lazy { FavoriteAdapter() }

    override fun init() {
        binding.recyclerView.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.fragmentComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun setupClickListeners() {
        binding.imageButtonClose.setOnClickListener { findNavController().popBackStack() }
        adapter.onPhotoItemClick = {
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToPhotoDetailFragment(photoId = it.id)
            )
        }
    }

    override fun setUpViewModelStateObservers() {
        viewModel.favoriteListLiveData.observeNonNull(viewLifecycleOwner) {viewState ->
            binding.viewState = viewState
            adapter.submitList(viewState.getData())

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.onPhotoItemClick = null
    }
}