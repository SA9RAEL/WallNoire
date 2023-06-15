package com.example.wallnoire.presentation.detail

import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.utils.Resource

fun photoDetailPageCombiner(
    favoritePhotoList: Resource<List<PhotoViewItem>>,
    resource: Resource<PhotoDetailViewItem>
): PhotoDetailFragmentViewState {
    var favoriteList = emptyList<PhotoViewItem>()
    if (favoritePhotoList is Resource.Success) favoriteList = favoritePhotoList.data
    return PhotoDetailFragmentViewState(resource, favoriteList)
}