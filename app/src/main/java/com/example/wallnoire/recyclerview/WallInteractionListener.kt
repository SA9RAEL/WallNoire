package com.example.wallnoire.recyclerview

import android.view.View
import com.example.wallnoire.model.domain.Data

interface WallInteractionListener {
    fun onClickItem(data: Data, view: View)
}