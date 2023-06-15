package com.example.wallnoire.presentation.detail

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.wallnoire.R
import com.example.wallnoire.base.domain.BaseFragmentViewState
import com.example.wallnoire.data.viewitem.PhotoDetailViewItem
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.utils.Resource
import com.example.wallnoire.utils.State

class PhotoDetailFragmentViewState(
    detailResource: Resource<PhotoDetailViewItem>,
    private val favoritePhotoList: List<PhotoViewItem>
) : BaseFragmentViewState<PhotoDetailViewItem, Resource<PhotoDetailViewItem>>(detailResource) {

    fun viewsEnabled() = getState() == State.SUCCESS

    fun isFavorite(): Boolean = favoritePhotoList.map { it.id }.contains(getData()?.id)

    fun getFavoriteIcon(context: Context): Drawable? {
        return if (isFavorite())
            ContextCompat.getDrawable(context, R.drawable.ic_favorite_black_24dp)
        else
            ContextCompat.getDrawable(context, R.drawable.ic_favorite_border_black_24dp)
    }

    fun getFavoriteIconVisibility(): Int =
        when (getState()) {
            State.SUCCESS -> View.VISIBLE
            else -> View.GONE
        }
}