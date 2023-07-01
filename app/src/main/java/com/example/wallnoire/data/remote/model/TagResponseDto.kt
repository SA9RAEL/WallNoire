package com.example.wallnoire.data.remote.model

import com.google.gson.annotations.SerializedName

data class TagResponseDto(
    @SerializedName("type") val type: String?,
    @SerializedName("title") val title: String?
)