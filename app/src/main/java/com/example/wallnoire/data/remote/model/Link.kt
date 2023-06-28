package com.example.wallnoire.data.remote.model

import com.google.gson.annotations.SerializedName

// Переименовать в LinkResponseDto
data class Link(
    @SerializedName("self") val self: String?,
    @SerializedName("html") val html: String?,
    @SerializedName("download") val download: String?,
    @SerializedName("download_location") val downloadLocation: String?
)