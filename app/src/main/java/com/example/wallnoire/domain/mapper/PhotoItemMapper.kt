package com.example.wallnoire.domain.mapper

import com.example.wallnoire.data.remote.model.response.PhotosResponse
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.data.viewitem.PhotosViewItem
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PhotoItemMapper @Inject constructor() : Mapper<PhotosResponse, PhotosViewItem> {
    override fun mapFrom(item: PhotosResponse): PhotosViewItem {
        return PhotosViewItem(
            totalPage = item.totalPages,
            photo = item.results.map { photo ->
                PhotoViewItem(
                    id = photo.id.orEmpty(),
                    photoUrl = photo.urls?.small.toString()
                )
            }
        )
    }
}