package com.example.wallnoire.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wallnoire.data.local.dao.FavoritePhotoDao
import com.example.wallnoire.data.local.entity.FavoritePhotoEntity

@Database(entities = [FavoritePhotoEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDataBase : RoomDatabase() {

    abstract fun getFavoritePhotoDao(): FavoritePhotoDao

    companion object {
        const val DB_MANE = "wall_database"
    }


}