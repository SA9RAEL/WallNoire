package com.example.wallnoire.domain.mapper

import com.example.wallnoire.data.remote.model.response.PhotosResponseDto
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.data.viewitem.PhotosViewItem
import javax.inject.Inject

class PhotoItemMapper @Inject constructor() : Mapper<PhotosResponseDto, PhotosViewItem> {
    override fun mapFrom(item: PhotosResponseDto): PhotosViewItem {
        return PhotosViewItem(
            totalPage = item.totalPages,
            photos = item.results.map { photo ->
                PhotoViewItem(
                    id = photo.id.orEmpty(),
                    photoUrl = photo.urls?.small.toString()
                )
            }
        )
    }
}