package com.example.wallnoire.presentation.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wallnoire.base.viewmodel.BaseViewModel
import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import com.example.wallnoire.domain.usecase.FavoriteUseCase
import com.example.wallnoire.domain.usecase.PhotoDetailUseCase
import com.example.wallnoire.domain.usecase.PhotoDownloadUseCase
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoDetailViewModel @Inject constructor(
    private val useCase: PhotoDetailUseCase,
    private val favoriteUseCase: FavoriteUseCase
) : BaseViewModel() {

    private val _viewState = MutableLiveData<PhotoDetailFragmentViewState>()
    val viewState: LiveData<PhotoDetailFragmentViewState> = _viewState

    private lateinit var id: String

    override fun handleIntent(extras: Bundle) {
        val args = PhotoDetailFragmentArgs.fromBundle(extras)
        this.id = args.photoId
    }

    fun getPhotoDetail() {
        combine(
            favoriteUseCase.getFavoriteList(),
            useCase.getPhotoDetail(id = id),
            ::photoDetailPageCombiner
        )
            .onEach { _viewState.value = it }
            .launchIn(viewModelScope)
    }

    fun addFavorite() {
        runIfDataIsNotNull { photoDetailViewItem ->
            viewModelScope.launch {
                favoriteUseCase.addFavorite(photoDetailViewItem)
            }
        }
    }

    fun deleteFavorite() {
        runIfDataIsNotNull { photoDetailViewItem ->
            viewModelScope.launch {
                favoriteUseCase.deleteFavorite(photoDetailViewItem.id)
            }
        }
    }

    private fun runIfDataIsNotNull(block: (PhotoDetailViewItem) -> Unit) {
        _viewState.value?.getData()?.let { block(it) }
    }

}