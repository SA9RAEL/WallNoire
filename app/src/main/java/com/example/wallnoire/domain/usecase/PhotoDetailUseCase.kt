package com.example.wallnoire.domain.usecase

import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import com.example.wallnoire.domain.mapper.PhotoDetailItemMapper
import com.example.wallnoire.domain.repository.RemoteRepository
import com.example.wallnoire.utils.Resource
import com.example.wallnoire.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoDetailUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val itemMapper: PhotoDetailItemMapper
) {
    fun getPhotoDetail(id: String): Flow<Resource<PhotoDetailViewItem>> =
        remoteRepository.getPhotoDetail(id).map { resource ->
            resource.map { response ->
                itemMapper.mapFrom(response)
            }
        }

}