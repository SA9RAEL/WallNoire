package com.example.wallnoire.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wallnoire.model.room.dao.WallDao
import com.example.wallnoire.model.room.data.WallEntity

@Database(entities = [WallEntity::class], version = 1, exportSchema = false)
abstract class WallDatabase : RoomDatabase() {

    abstract fun wallDao(): WallDao

    companion object {
        const val DB_MANE = "wall_database"
    }


}