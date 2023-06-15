package com.example.wallnoire.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wallnoire.base.viewmodel.BaseViewModel
import com.example.wallnoire.domain.usecase.FavoriteUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val useCase: FavoriteUseCase) :
    BaseViewModel() {
    private val _favoriteListLiveData = MutableLiveData<FavoriteFragmentViewState>()
    val favoriteListLiveData: LiveData<FavoriteFragmentViewState> = _favoriteListLiveData

    init {
        useCase.getFavoriteList()
            .onEach { _favoriteListLiveData.value = FavoriteFragmentViewState(it) }
            .launchIn(viewModelScope)
    }

}