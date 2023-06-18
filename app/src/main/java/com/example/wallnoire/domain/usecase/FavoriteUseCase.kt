package com.example.wallnoire.domain.usecase

import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.domain.mapper.FavoriteItemMapper
import com.example.wallnoire.domain.repository.LocalRepository
import com.example.wallnoire.utils.Resource
import com.example.wallnoire.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class FavoriteUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val itemMapper: FavoriteItemMapper
) {
    fun getFavoriteList(): Flow<Resource<List<PhotoViewItem>>> =
        localRepository.getFavoriteList().map { resource ->
            resource.map { list ->
                list.map { entity ->
                    itemMapper.mapFrom(entity)
                }
            }
        }

    suspend fun addFavorite(photoDetailViewItem: PhotoDetailViewItem) {
        localRepository.addFavorite(photoDetailViewItem)
    }

    suspend fun deleteFavorite(photoId: String) {
        localRepository.deleteFavorite(photoId = photoId)
    }
}