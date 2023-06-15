package com.example.wallnoire.presentation.favorite

import com.example.wallnoire.base.domain.BaseFragmentViewState
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.utils.Resource

class FavoriteFragmentViewState(resource: Resource<List<PhotoViewItem>>) :
    BaseFragmentViewState<List<PhotoViewItem>, Resource<List<PhotoViewItem>>>(resource) {
}