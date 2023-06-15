package com.example.wallnoire.presentation.photos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.wallnoire.base.viewmodel.BaseViewModel
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.domain.usecase.CategoryUseCase
import com.example.wallnoire.domain.usecase.PhotosUseCase
import com.example.wallnoire.presentation.photos.helper.MovieItemPaginationHelper
import com.example.wallnoire.utils.Resource
import com.example.wallnoire.view.categoryview.Category
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PhotosViewModel @Inject constructor(categoryUseCase: CategoryUseCase, private val photosUseCase: PhotosUseCase ) : BaseViewModel() {
    private val _viewState = MutableLiveData<PhotosFragmentViewState>()
    val viewState: LiveData<PhotosFragmentViewState> = _viewState

    var page = FIRST_PAGE
    var selectedCategoryIndex = 0
    val categoryList = ArrayList<Category>()
    private var paginationHelper = MovieItemPaginationHelper(this)

    init {
        categoryList.addAll(categoryUseCase.getCategories())
    }

    fun getMovieList(): LiveData<PagedList<PhotoViewItem>> {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setInitialLoadSizeHint(30)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(true)
            .build()
        return initPagedListBuilder(config).build()
    }

    private fun initPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, PhotoViewItem> {
        val dataSourceFactory = object : DataSource.Factory<Int, PhotoViewItem>() {
            override fun create(): DataSource<Int, PhotoViewItem> {
                return paginationHelper
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    fun getPhotosByQuery(updateViewState: Boolean, block: (PhotosFragmentViewState) -> Unit) {
        photosUseCase.getPhotosByQuery(query = categoryList[selectedCategoryIndex].name, page = page)
            .onEach { response ->
                val viewState = PhotosFragmentViewState(response)
                block(viewState)
                if (updateViewState || response is Resource.Success || response is Resource.Error) {
                    _viewState.value = viewState
                }

            }.launchIn(viewModelScope)
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}