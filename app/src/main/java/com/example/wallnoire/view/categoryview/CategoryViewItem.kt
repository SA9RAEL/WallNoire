package com.example.wallnoire.view.categoryview

data class CategoryViewItem(
    private val category: Category,
    private val tag: Int
) {
    fun getCategory() = category
    fun getTag() = tag
}