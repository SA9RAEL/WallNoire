package com.example.wallnoire.di

import android.content.Context
import com.example.wallnoire.di.modules.LocalDataModule
import com.example.wallnoire.di.modules.NetworkModule
import com.example.wallnoire.di.modules.ViewModelModule
import com.example.wallnoire.presentation.detail.PhotoDetailFragment
import com.example.wallnoire.presentation.favorite.FavoriteFragment
import com.example.wallnoire.presentation.home.HomeActivity
import com.example.wallnoire.presentation.photos.PhotosFragment
import com.example.wallnoire.presentation.splash.SplashActivity
import com.example.wallnoire.service.PhotoDownloadService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        LocalDataModule::class,
        NetworkModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun withContext(@BindsInstance context: Context): Builder
        fun build(): AppComponent

    }

    fun inject(activity: HomeActivity)
    fun inject(activity: SplashActivity)
    fun inject(fragment: PhotosFragment)
    fun inject(fragment: PhotoDetailFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(service: PhotoDownloadService)

}