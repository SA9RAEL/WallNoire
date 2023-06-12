package com.example.wallnoire.ui.fragments.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId) {
//
//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
}