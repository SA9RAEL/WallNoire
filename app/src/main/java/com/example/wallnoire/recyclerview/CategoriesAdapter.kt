package com.example.wallnoire.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.depapel.R
import com.example.depapel.databinding.CategoryRowBinding
import com.example.wallnoire.model.domain.Category

class CategoriesAdapter(
    private val topicItem: List<Category>,
    private val listener: CategoryInteractionListener
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoryRowBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = topicItem[position]
        holder.binding.apply {
            categoryTextView.text = currentCategory.categoryName

            Glide.with(holder.itemView.context)
                .load(currentCategory.imageUrl)
                .centerCrop()
                .error(R.color.blue)
                .into(categoryImageView)
        }
        holder.itemView.setOnClickListener {
            listener.onClickCategory(currentCategory, it)
        }
    }

    override fun getItemCount() = topicItem.size
}