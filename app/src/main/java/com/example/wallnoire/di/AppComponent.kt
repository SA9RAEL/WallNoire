package com.example.wallnoire.di

import android.content.Context
import com.example.wallnoire.ui.fragments.CategoriesFragment
import com.example.wallnoire.ui.fragments.DownloadFragment
import com.example.wallnoire.ui.fragments.SpecificCategoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AllModules::class])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: CategoriesFragment)
    fun inject(fragment: DownloadFragment)
    fun inject(fragment: SpecificCategoryFragment)

}