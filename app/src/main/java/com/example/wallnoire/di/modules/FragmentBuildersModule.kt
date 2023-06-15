package com.example.wallnoire.di.modules

import com.example.wallnoire.presentation.detail.PhotoDetailFragment
import com.example.wallnoire.presentation.favorite.FavoriteFragment
import com.example.wallnoire.presentation.photos.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuildersModule {

    @get:ContributesAndroidInjector
    val contributePhotosFragment: PhotosFragment

    @get:ContributesAndroidInjector
    val contributePhotoDetailFragment: PhotoDetailFragment

    @get:ContributesAndroidInjector
    val contributeFavoriteFragment: FavoriteFragment


}
