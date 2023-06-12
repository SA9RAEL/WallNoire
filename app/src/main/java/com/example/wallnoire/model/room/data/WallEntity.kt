package com.example.wallnoire.model.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WALL_TABLE")
data class WallEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val wallUrl: String?

)