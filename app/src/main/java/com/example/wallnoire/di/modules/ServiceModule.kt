package com.example.wallnoire.di.modules

import com.example.wallnoire.service.PhotoDownloadService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ServiceModule {
    @ContributesAndroidInjector
    fun contributesPhotoDownloadService(): PhotoDownloadService
}
