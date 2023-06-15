package com.example.wallnoire.base.adapter

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    protected val context: Context = itemView.context
    protected val resources: Resources = itemView.resources
    abstract fun bind(data: T)
    val containerView: View = itemView
}