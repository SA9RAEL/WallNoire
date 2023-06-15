package com.example.wallnoire.di

import android.content.Context
import com.example.wallnoire.WallApplication
import com.example.wallnoire.di.modules.ActivityModule
import com.example.wallnoire.di.modules.FragmentBuildersModule
import com.example.wallnoire.di.modules.LocalDataModule
import com.example.wallnoire.di.modules.NetworkModule
import com.example.wallnoire.di.modules.ServiceModule
import com.example.wallnoire.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentBuildersModule::class,
        LocalDataModule::class,
        NetworkModule::class,
        ServiceModule::class,
        ViewModelModule::class]
)
@Singleton
interface AppComponent : AndroidInjector<WallApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AndroidInjector<WallApplication>

    }

}