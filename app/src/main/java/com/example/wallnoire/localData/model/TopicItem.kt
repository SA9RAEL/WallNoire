package com.example.wallnoire.localData.model

import com.google.gson.annotations.SerializedName

data class TopicItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("imageUrl")
    val imageUrl: String
)