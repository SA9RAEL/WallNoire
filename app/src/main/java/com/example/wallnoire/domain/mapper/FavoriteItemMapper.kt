package com.example.wallnoire.domain.mapper

import com.example.wallnoire.data.local.entity.FavoritePhotoEntity
import com.example.wallnoire.data.viewitem.PhotoViewItem
import javax.inject.Inject
import javax.inject.Singleton

// тут
@Singleton
class FavoriteItemMapper @Inject constructor() : Mapper<FavoritePhotoEntity, PhotoViewItem> {
    override fun mapFrom(item: FavoritePhotoEntity): PhotoViewItem {
        return PhotoViewItem(
            id = item.id,
            photoUrl = item.imageUrl
        )
    }
}