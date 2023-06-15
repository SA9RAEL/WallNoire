package com.example.wallnoire.presentation.photos

import com.example.wallnoire.base.domain.BaseFragmentViewState
import com.example.wallnoire.data.viewitem.PhotosViewItem
import com.example.wallnoire.utils.Resource

class PhotosFragmentViewState(photosResource: Resource<PhotosViewItem>) :
    BaseFragmentViewState<PhotosViewItem, Resource<PhotosViewItem>>(photosResource) {
}