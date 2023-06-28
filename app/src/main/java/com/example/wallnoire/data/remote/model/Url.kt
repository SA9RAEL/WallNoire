package com.example.wallnoire.data.remote.model

import com.google.gson.annotations.SerializedName

// Переименовать в UrlResponseDto
data class Url(
    @SerializedName("raw") val raw: String?,
    @SerializedName("full") val full: String?,
    @SerializedName("regular") val regular: String?,
    @SerializedName("small") val small: String?,
    @SerializedName("thumb") val thumb: String?
)