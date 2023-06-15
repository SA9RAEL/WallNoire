package com.example.wallnoire.base.domain

import com.example.wallnoire.utils.Resource
import com.example.wallnoire.utils.State

open class BaseFragmentViewState<V, T : Resource<V>>(private val resource: T) {

    @Suppress("UNCHECKED_CAST")
    fun getData(): V? {
        return when (resource) {
            is Resource.Success<*> -> resource.data as V?
            else -> null
        }
    }

    open fun getState(): State {
        return when (resource) {
            is Resource.Loading -> State.LOADING
            is Resource.Success<*> -> State.SUCCESS
            is Resource.Error -> State.ERROR
            is Resource.Empty -> State.EMPTY
            else -> State.NONE
        }
    }
}