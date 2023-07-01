package com.example.wallnoire.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponseDto(
    @SerializedName("id") val id: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("portfolio_url") val portfolioUrl: String?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("links") val links: LinkResponseDto
)