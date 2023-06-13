package com.example.wallnoire.domain.repository

import com.example.wallnoire.data.local.entity.FavoritePhotoEntity
import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import com.example.wallnoire.view.categoryview.Category
import com.example.wallnoire.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getCategories() : ArrayList<Category>

    suspend fun addFavorite(photoDerailViewItem: PhotoDetailViewItem)

    suspend fun deleteFavorite(photoId: String)

    fun getFavoriteList(): Flow<Resource<List<FavoritePhotoEntity>>>
}