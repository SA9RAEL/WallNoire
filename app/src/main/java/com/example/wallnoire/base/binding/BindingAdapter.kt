package com.example.wallnoire.base.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.example.wallnoire.R
import com.example.wallnoire.utils.State
import com.example.wallnoire.view.BaseView

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let { imageUrl ->
        Glide
            .with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}

@BindingAdapter("state")
fun setBaseViewState(baseView: BaseView, state: State?) {
    BaseView.Builder().view(baseView).state(state).build()
}

@BindingAdapter("drawable")
fun setImageDrawable(imageView: ImageView, drawable: Drawable?) {
    drawable?.let { imageView.setImageDrawable(it) }
}