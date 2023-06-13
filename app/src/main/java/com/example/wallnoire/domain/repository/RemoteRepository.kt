package com.example.wallnoire.domain.repository

import com.example.wallnoire.data.remote.model.Photo
import com.example.wallnoire.data.remote.model.response.PhotosResponse
import com.example.wallnoire.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface RemoteRepository {
    fun getPhotosByQuery(query: String, page: Int): Flow<Resource<PhotosResponse>>
    fun getPhotoDetail(id: String): Flow<Resource<Photo>>
    fun downloadImage(url: String): Flow<Resource<ResponseBody>>
}