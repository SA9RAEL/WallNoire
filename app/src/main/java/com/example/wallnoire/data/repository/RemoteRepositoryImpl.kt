package com.example.wallnoire.data.repository

import com.example.wallnoire.data.remote.model.PhotoResponseDto
import com.example.wallnoire.data.remote.model.response.PhotosResponseDto
import com.example.wallnoire.domain.repository.RemoteRepository
import com.example.wallnoire.network.WallApiService
import com.example.wallnoire.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteRepositoryImpl @Inject constructor(private val api: WallApiService) : RemoteRepository {
    override fun getPhotosByQuery(query: String, page: Int): Flow<Resource<PhotosResponseDto>> = flow {
        val responseCall = api.getPhotosByQuery(query, page)
        emit(Resource.Success(responseCall))
    }.execute()

    override fun getPhotoDetail(id: String): Flow<Resource<PhotoResponseDto>> = flow {
        val responseCall = api.getPhotoDetail(id)
        emit(Resource.Success(responseCall))
    }.execute()

    override fun downloadImage(url: String): Flow<Resource<ResponseBody>> = flow {
        val responseCall = api.downloadImage(url)
        emit(Resource.Success(responseCall))
    }.execute()

    private fun <T> Flow<Resource<T>>.execute(): Flow<Resource<T>> =
        onStart { emit(Resource.Loading) }
            .catch { throwable -> emit(Resource.Error(throwable)) }
            .flowOn(Dispatchers.IO)

}