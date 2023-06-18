package com.example.wallnoire.domain.usecase

import com.example.wallnoire.data.viewitem.PhotosViewItem
import com.example.wallnoire.domain.mapper.PhotoItemMapper
import com.example.wallnoire.domain.repository.RemoteRepository
import com.example.wallnoire.utils.Resource
import com.example.wallnoire.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class PhotosUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val itemMapper: PhotoItemMapper
) {
    fun getPhotosByQuery(query: String, page: Int): Flow<Resource<PhotosViewItem>> {
        return remoteRepository.getPhotosByQuery(query, page).map { resource ->
            resource.map { response ->
                itemMapper.mapFrom(response)
            }

        }
    }
}