package com.example.wallnoire.recyclerview

import android.view.View
import com.example.wallnoire.localData.model.TopicItem

interface CategoryInteractionListener {
    fun onClickCategory(topicItem: TopicItem, view: View)
}