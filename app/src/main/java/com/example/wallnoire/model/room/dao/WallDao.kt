package com.example.wallnoire.model.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallnoire.model.room.data.WallEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface WallDao {

    @Query("SELECT * FROM wall_table")
    fun getAllWall(): Observable<List<WallEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(wall: WallEntity): Completable

}