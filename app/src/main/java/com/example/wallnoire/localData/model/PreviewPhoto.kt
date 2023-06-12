package com.example.wallnoire.localData.model

import com.google.gson.annotations.SerializedName

data class PreviewPhoto(
    @SerializedName("blurHash")
    val blurHash: String?,
    @SerializedName("urls")
    val urls: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("imageId")
    val imageId: String
)
