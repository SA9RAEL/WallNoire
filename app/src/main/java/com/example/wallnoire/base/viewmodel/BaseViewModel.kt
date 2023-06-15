package com.example.wallnoire.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

abstract class BaseViewModel : ViewModel() {

    open fun handleIntent(extras: Bundle) {}
    var firstOpen = true

    suspend inline fun <T> Flow<T>.sentRequest(crossinline onComplete: (T) -> Unit) {
        onEach { data -> onComplete(data) }.collect()
    }
}