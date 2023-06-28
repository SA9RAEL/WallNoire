package com.example.wallnoire.domain.mapper

import com.example.wallnoire.base.extention.orEmpty
import com.example.wallnoire.data.remote.model.Photo
import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import javax.inject.Inject
import javax.inject.Singleton

// тут
@Singleton
class PhotoDetailItemMapper @Inject constructor() : Mapper<Photo, PhotoDetailViewItem> {
    override fun mapFrom(item: Photo): PhotoDetailViewItem {
        return PhotoDetailViewItem(
            id = item.id.orEmpty(),
            source = item.links?.html.orEmpty(),
            photoOwnerName = item.user?.name.orEmpty(),
            photoOwnerProfile = item.user?.links?.html.orEmpty(),
            license = "Unsplash License",
            rawImageUrl = item.urls?.raw.orEmpty(),
            fullImageUrl = item.urls?.full.orEmpty(),
            regularImageUrl = item.urls?.regular.orEmpty(),
            smallImageUrl = item.urls?.small.orEmpty(),
            thumbImageUrl = item.urls?.thumb.orEmpty()
        )
    }

}

