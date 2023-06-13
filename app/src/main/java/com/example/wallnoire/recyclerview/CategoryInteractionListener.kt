package com.example.wallnoire.recyclerview

import android.view.View
import com.example.wallnoire.model.domain.Category

interface CategoryInteractionListener {
    fun onClickCategory(category: Category, view: View)
}