package com.example.wallnoire.presentation.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallnoire.R
import com.example.wallnoire.databinding.ItemLoadMoreBinding
import com.example.wallnoire.databinding.ItemPhotoBinding
import com.example.wallnoire.base.adapter.BaseViewHolder
import com.example.wallnoire.data.viewitem.PhotoViewItem
import com.example.wallnoire.presentation.common.holder.LoadMoreViewHolder
import com.example.wallnoire.presentation.common.holder.PhotoItemViewHolder
import com.example.wallnoire.utils.PhotoItemDiffCallback

class PhotosAdapter :
    PagedListAdapter<PhotoViewItem, BaseViewHolder<*>>(PhotoItemDiffCallback.diffCallback) {
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

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PhotoItemViewHolder -> holder.bind(getItem(position))
        }
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
        return if (itemCount > 1 && itemCount == position + 1) ItemType.TYPE_LOAD_MORE.viewType()
        else ItemType.TYPE_PHOTO.viewType()
    }

    override fun getItemId(position: Int): Long = position.toLong()

    val spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return if (getItemViewType(position) == ItemType.TYPE_LOAD_MORE.viewType()) {
                2
            } else {
                if (position % 3 == 0) 2 else 1
            }
        }
    }
}