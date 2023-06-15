package com.example.wallnoire.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallnoire.di.ViewModelFactory
import com.example.wallnoire.di.ViewModelKey
import com.example.wallnoire.presentation.detail.PhotoDetailViewModel
import com.example.wallnoire.presentation.favorite.FavoriteViewModel
import com.example.wallnoire.presentation.photos.PhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun ViewModelFactory.viewModelFactory(): ViewModelProvider.Factory

    @Binds
    @[IntoMap ViewModelKey(PhotosViewModel::class)]
    fun PhotosViewModel.bindsPhotosViewModel(): ViewModel


    @Binds
    @[IntoMap ViewModelKey(PhotoDetailViewModel::class)]
    fun PhotoDetailViewModel.bidsPhotoDetailViewModel(): ViewModel


    @Binds
    @[IntoMap ViewModelKey(FavoriteViewModel::class)]
    fun FavoriteViewModel.bidsFavoriteViewModel(): ViewModel
}