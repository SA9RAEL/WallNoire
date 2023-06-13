package com.example.wallnoire.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallnoire.di.ViewModelFactory
import com.example.wallnoire.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory

    @Binds
    @[IntoMap ViewModelKey(PhotosViewModel::class)]
    val PhotosViewModel.bindsPhotosViewModel: ViewModel


    @Binds
    @[IntoMap ViewModelKey(PhotoDetailViewModel::class)]
    val PhotoDetailViewModel.bidsPhotoDetailViewModel: ViewModel


    @Binds
    @[IntoMap ViewModelKey(FavoriteViewModel::class)]
    val FavoriteViewModel.bidsFavoriteViewModel: ViewModel
}