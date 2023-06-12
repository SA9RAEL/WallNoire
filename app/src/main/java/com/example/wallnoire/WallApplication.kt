package com.example.wallnoire

import android.app.Application
import com.example.wallnoire.di.AppComponent
import com.example.wallnoire.di.DaggerAppComponent

class WallApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().withContext(this).build()
    }
}