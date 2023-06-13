package com.example.wallnoire.data.repository

import com.example.wallnoire.data.local.dao.FavoritePhotoDao
import com.example.wallnoire.data.local.entity.FavoritePhotoEntity
import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import com.example.wallnoire.domain.repository.LocalRepository
import com.example.wallnoire.utils.Resource
import com.example.wallnoire.view.categoryview.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepositoryImpl @Inject constructor(
    private val favoritePhotoDao: FavoritePhotoDao
) : LocalRepository {

    override fun getCategories(): ArrayList<Category> {
        return arrayListOf(
            Category("Wallpapers"),
            Category("Abstract"),
            Category("Animal"),
            Category("Art"),
            Category("Black"),
            Category("Car"),
            Category("Cat"),
            Category("City"),
            Category("Dog"),
            Category("Flower"),
            Category("Funny"),
            Category("Sport"),
            Category("Summer"),
            Category("Winter"),
        )
    }

    override suspend fun addFavorite(photoDerailViewItem: PhotoDetailViewItem) {
        favoritePhotoDao.addPhotoToFavorite(
            FavoritePhotoEntity(
                id = photoDerailViewItem.id,
                imageUrl = photoDerailViewItem.smallImageUrl
            )
        )
    }

    override suspend fun deleteFavorite(photoId: String) {
        return favoritePhotoDao.deletePhotoFromFavorite(photoId)
    }

    override fun getFavoriteList(): Flow<Resource<List<FavoritePhotoEntity>>> {
        return favoritePhotoDao.getFavoritePhotos().map { listOfPhotos ->
            if (listOfPhotos.isEmpty()) Resource.Empty
            else Resource.Success(listOfPhotos)
        }.catch { Resource.Error(it) }
    }

}