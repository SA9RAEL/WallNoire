package com.example.wallnoire.data.remote.model

import com.google.gson.annotations.SerializedName

// Переименовать в TagResponseDto
data class Tag(
    @SerializedName("type") val type: String?,
    @SerializedName("title") val title: String?
)