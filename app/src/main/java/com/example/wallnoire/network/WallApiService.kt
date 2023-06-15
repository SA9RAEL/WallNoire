package com.example.wallnoire.network

import com.example.wallnoire.data.remote.model.Photo
import com.example.wallnoire.data.remote.model.response.PhotosResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url

interface WallApiService {
    @GET("search/photos")
    suspend fun getPhotosByQuery(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30
    ): PhotosResponse

    @GET("photos/{id}")
    suspend fun getPhotoDetail(@Path("id") id: String): Photo

    @Streaming
    @GET
    suspend fun downloadImage(@Url url: String): ResponseBody

}