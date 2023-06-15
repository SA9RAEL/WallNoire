package com.example.wallnoire.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_photos")
data class FavoritePhotoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "code") var code: Int = 0,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "image_url") val imageUrl: String

)