package com.example.wallnoire.di.modules

import android.content.Context
import androidx.room.Room
import com.example.wallnoire.data.local.FavoriteDataBase
import com.example.wallnoire.data.repository.LocalRepositoryImpl
import com.example.wallnoire.data.repository.RemoteRepositoryImpl
import com.example.wallnoire.domain.repository.LocalRepository
import com.example.wallnoire.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class LocalDataModule {

    companion object {
        @Singleton
        @Provides
        fun provideDb(context: Context): FavoriteDataBase =
            Room.databaseBuilder(context, FavoriteDataBase::class.java, FavoriteDataBase.DB_MANE)
                .build()

        @Singleton
        @Provides
        fun provideFavoritePhotoDao(database: FavoriteDataBase) = database.getFavoritePhotoDao()
    }

  @Singleton
  @Binds
  abstract fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

  @Singleton
  @Binds
  abstract fun bindRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

}