package com.example.wallnoire.base.extention

import android.content.Context
import androidx.fragment.app.Fragment

fun Fragment.runContextNotNull(block: (Context) -> Unit) {
    this.context?.let { block(it) }
}