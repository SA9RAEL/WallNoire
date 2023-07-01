package com.example.wallnoire.data.remote.model.response

import com.example.wallnoire.data.remote.model.PhotoResponseDto
import com.google.gson.annotations.SerializedName

data class PhotosResponseDto (
    @SerializedName("total") val total: Int,
    @SerializedName ("total_pages") val totalPages: Int,
    @SerializedName ("results") val results: ArrayList<PhotoResponseDto>
)