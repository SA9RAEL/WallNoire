package com.example.wallnoire.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.wallnoire.R
import com.example.wallnoire.databinding.ItemLoadMoreBinding
import com.example.wallnoire.databinding.ItemPhotoBinding
import com.example.wallnoire.base.adapter.BaseViewHolder
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.presentation.common.holder.LoadMoreViewHolder
import com.example.wallnoire.presentation.common.holder.PhotoItemViewHolder
import com.example.wallnoire.utils.PhotoItemDiffCallback

class FavoriteAdapter :
    ListAdapter<PhotoViewItem, BaseViewHolder<*>>(PhotoItemDiffCallback.diffCallback) {
    var onPhotoItemClick: ((PhotoViewItem) -> Unit)? = null

    enum class ItemType {
        TYPE_PHOTO {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                layoutInflater: LayoutInflater,
                onPhotoItemClick: ((PhotoViewItem) -> Unit)?
            ): BaseViewHolder<*> {
                val binding = DataBindingUtil.inflate<ItemPhotoBinding>(
                    layoutInflater,
                    R.layout.item_photo,
                    parent,
                    false
                )
                return PhotoItemViewHolder(binding, onPhotoItemClick)
            }
        },
        TYPE_LOAD_MORE {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                layoutInflater: LayoutInflater,
                onPhotoItemClick: ((PhotoViewItem) -> Unit)?
            ): BaseViewHolder<*> {
                val binding = DataBindingUtil.inflate<ItemLoadMoreBinding>(
                    layoutInflater,
                    R.layout.item_load_more,
                    parent,
                    false
                )
                return LoadMoreViewHolder(binding)
            }
        };

        abstract fun onCreateViewHolder(
            parent: ViewGroup,
            layoutInflater: LayoutInflater,
            onPhotoItemClick: ((PhotoViewItem) -> Unit)?
        ): BaseViewHolder<*>

        fun viewType(): Int = ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemType.values()[viewType].onCreateViewHolder(
            parent,
            layoutInflater,
            onPhotoItemClick
        )
    }

    override fun getItemViewType(position: Int): Int {
        return ItemType.TYPE_PHOTO.viewType()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (position == itemCount) return
        when (holder) {
            is PhotoItemViewHolder -> holder.bind(getItem(position))
        }
    }
}
