package com.example.wallnoire.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallnoire.data.local.entity.FavoritePhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePhotoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPhotoToFavorite(favoritePhotoEntity: FavoritePhotoEntity)

    @Query("DELETE FROM fav_photos WHERE id=:photoId")
    suspend fun deletePhotoFromFavorite(photoId: String)

    @Query("SELECT * FROM fav_photos ORDER BY code DESC")
    fun getFavoritePhotos(): Flow<List<FavoritePhotoEntity>>

}