package com.example.wallnoire.base.extention

import android.app.Activity
import android.app.Service
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.wallnoire.WallApplication
import com.example.wallnoire.di.AppComponent

fun Fragment.runContextNotNull(block: (Context) -> Unit) {
    this.context?.let { block(it) }
}

fun Fragment.fragmentComponent(): AppComponent =
    (this.context?.applicationContext as WallApplication).appComponent

fun Activity.activityComponent(): AppComponent =
    (applicationContext as WallApplication).appComponent

fun Service.serviceComponent(): AppComponent =
    (applicationContext as WallApplication).appComponent