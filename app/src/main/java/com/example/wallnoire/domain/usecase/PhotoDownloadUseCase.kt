package com.example.wallnoire.domain.usecase

import com.example.wallnoire.domain.repository.RemoteRepository
import com.example.wallnoire.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PhotoDownloadUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) {
    fun downloadImage(url: String): Flow<Resource<ResponseBody>> =
        remoteRepository.downloadImage(url)

}