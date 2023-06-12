package com.example.wallnoire.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.example.depapel.R
import com.example.depapel.databinding.ItemRecyclerViewBinding
import com.example.wallnoire.model.domain.Data
import com.example.wallnoire.utils.BlurHashDecoder

class RecyclerViewAdapter(private val listener: WallInteractionListener) :
    PagingDataAdapter<Data, RecyclerViewAdapter.WallViewHolder>(WALLS_COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerViewAdapter.WallViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.WallViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return WallViewHolder(inflater)
    }

    inner class WallViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRecyclerViewBinding.bind(view)

        fun bind(data: Data) {
            val blurHashAsDrawable = BlurHashDecoder.blurHashBitmap(itemView.resources, data)

            Glide.with(itemView.context)
                .asBitmap()
                .load(data.smallImageUrl)
                .centerCrop()
                .transition(BitmapTransitionOptions.withCrossFade(80))
                .error(blurHashAsDrawable)
                .into(binding.imageView)

            itemView.setOnClickListener { listener.onClickItem(data, it) }
        }
    }

    companion object {
        private val WALLS_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.blurHash == newItem.blurHash
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }

}