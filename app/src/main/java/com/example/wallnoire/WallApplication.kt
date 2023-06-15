package com.example.wallnoire

import androidx.viewbinding.BuildConfig
import com.example.wallnoire.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WallApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
       DaggerAppComponent.factory().create(this)

}