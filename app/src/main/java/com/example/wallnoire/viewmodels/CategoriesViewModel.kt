package com.example.wallnoire.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wallnoire.di.AllModules
import com.example.wallnoire.localData.repository.WallRepositoryImpl
import com.example.wallnoire.model.domain.Data
import com.example.wallnoire.model.paging.CategoryPagingSource
import com.example.wallnoire.model.repository.WallRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "CategoriesViewModel"

class CategoriesViewModel @AssistedInject constructor(
    private val repository: WallRepository,
    @Assisted private val topicId: Int
) : ViewModel() {

    var data = MutableLiveData<PagingData<Data>>()


    init {
//        viewModelScope.launch {
//            loadCategoryToRandom("categoryId").collect {
//                data.postValue(it)
//            }
//        }
        Log.e(TAG, "$topicId")

    }

    private fun loadCategoryToRandom(category: String): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(30),
            pagingSourceFactory = { CategoryPagingSource(repository.wallApiService(), category) }
        ).flow.cachedIn(viewModelScope)

    }

    @AssistedFactory
    interface CategoriesViewModelFactory {

        fun create(topicId: Int): CategoriesViewModel

    }

}