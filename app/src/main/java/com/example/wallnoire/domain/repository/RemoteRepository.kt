package com.example.wallnoire.domain.repository

import com.example.wallnoire.data.remote.model.PhotoResponseDto
import com.example.wallnoire.data.remote.model.response.PhotosResponseDto
import com.example.wallnoire.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface RemoteRepository {
    fun getPhotosByQuery(query: String, page: Int): Flow<Resource<PhotosResponseDto>>
    fun getPhotoDetail(id: String): Flow<Resource<PhotoResponseDto>>
    fun downloadImage(url: String): Flow<Resource<ResponseBody>>
}