package com.example.wallnoire.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.wallnoire.localData.repository.WallRepositoryImpl
import com.example.wallnoire.model.room.WallDatabase
import com.example.wallnoire.network.WallApiService
import com.example.wallnoire.model.repository.WallRepository
import com.example.wallnoire.viewmodels.CategoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.multibindings.IntoMap
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://api.unsplash.com/"

@Module
abstract class AllModules {

    companion object {

        @Provides
        @Reusable
        fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        @Provides
        @Reusable
        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    httpLoggingInterceptor()
                )
                .build()
        }

        @Provides
        @Reusable
        fun provideRetrofit(): WallApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                provideHttpClient()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WallApiService::class.java)

        @Singleton
        @Provides
        fun provideDb(context: Context): WallDatabase =
            Room.databaseBuilder(context, WallDatabase::class.java, WallDatabase.DB_MANE)
                .build()

    }

    @Binds
    abstract fun bindsRepository(wallRepositoryImpl: WallRepositoryImpl): WallRepository

//    @Binds
//    @[IntoMap ViewModelKey(SearchViewModel::class)]
//    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}