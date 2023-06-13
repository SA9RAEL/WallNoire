package com.example.wallnoire.viewmodels
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import androidx.paging.cachedIn
//import com.example.wallnoire.model.domain.Data
//import com.example.wallnoire.localData.repository.WallRepository
//import com.khater.retromvvm.model.paging.CategoryPagingSource
//import dagger.assisted.Assisted
//import dagger.assisted.AssistedFactory
//import dagger.assisted.AssistedInject
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.launch
//
//private const val TAG = "CategoriesViewModel"
//
//class CategoriesViewModel @AssistedInject constructor(
//    private val repository: WallRepository,
//    @Assisted private val categoryId: String
//) : ViewModel() {
//
//    var data = MutableLiveData<PagingData<Data>>()
//
//
//    init {
//        viewModelScope.launch {
//            loadCategoryToRandom("categoryId").collect {
//                data.postValue(it)
//            }
//        }
//        Log.e(TAG, categoryId)
//
//    }
//
//    private fun loadCategoryToRandom(category: String): Flow<PagingData<Data>> {
//        return Pager(config = PagingConfig(30),
//            pagingSourceFactory = { CategoryPagingSource(repository.wallApiService(), category) }
//        ).flow.cachedIn(viewModelScope)
//    }
//
//    @AssistedFactory
//    interface CategoriesViewModelFactory {
//        fun create(categoryId: String): CategoriesViewModel
//    }
//
//}
//
////class CategoriesViewModelFactory(private val categoryID: String) : ViewModelProvider.Factory {
////
////    override fun <T : ViewModel> create(modelClass: Class<T>): T {
////
////        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
////            return CategoriesViewModel(categoryID) as T
////        }
////        throw IllegalArgumentException("Unknown ViewModel class")
////    }
////}