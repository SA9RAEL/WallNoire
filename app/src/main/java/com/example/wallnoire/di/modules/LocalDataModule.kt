package com.example.wallnoire.di.modules

import android.content.Context
import androidx.room.Room
import com.example.wallnoire.data.local.FavoriteDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideDb(context: Context): FavoriteDataBase =
        Room.databaseBuilder(context, FavoriteDataBase::class.java, FavoriteDataBase.DB_MANE)
            .build()

    @Singleton
    @Provides
    fun provideFavoritePhotoDao(database: FavoriteDataBase) = database.getFavoritePhotoDao()

}