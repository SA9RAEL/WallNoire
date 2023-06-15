package com.example.wallnoire.di.modules

import com.example.wallnoire.presentation.home.HomeActivity
import com.example.wallnoire.presentation.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    fun bindMainActivity() : HomeActivity

    @ContributesAndroidInjector
    fun bindSplashActivity(): SplashActivity

}