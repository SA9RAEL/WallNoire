package com.example.wallnoire.presentation.common.holder

import com.example.wallnoire.databinding.ItemPhotoBinding
import com.example.wallnoire.base.adapter.BaseViewHolder
import com.example.wallnoire.data.viewitem.PhotoViewItem

class PhotoItemViewHolder(
    val binding: ItemPhotoBinding,
    private val onPhotoItemClick: ((PhotoViewItem) -> Unit)?
) : BaseViewHolder<PhotoViewItem?>(binding.root) {

    override fun bind(data: PhotoViewItem?) {
        if (data != null) {
            binding.photo = data
            binding.root.setOnClickListener { onPhotoItemClick?.invoke(data) }
            binding.executePendingBindings()
        }
    }
}