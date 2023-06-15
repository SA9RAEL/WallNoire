package com.example.wallnoire.base.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.wallnoire.R
import com.example.wallnoire.base.extention.orFalse
import com.example.wallnoire.base.extention.runContextNotNull
import com.example.wallnoire.data.enum.SnackBarType
import com.example.wallnoire.base.viewmodel.BaseViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @get:LayoutRes
    abstract val layoutResourceId: Int
    lateinit var binding: DB
    lateinit var viewModel: VM
    abstract val classTypeOfViewModel: Class<VM>
    private var hasRequestSend = false
    private var progressDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[classTypeOfViewModel]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            viewModel.handleIntent(it)
        }

        setUpViewModelStateObservers()
        setupClickListeners()
        init()
        if (!hasRequestSend) {
            initStartRequest()
            hasRequestSend = true
        }
        viewModel.firstOpen = false
    }

    open fun initStartRequest() {}
    open fun init() {}
    open fun setUpViewModelStateObservers() {}
    open fun setupClickListeners() {}

    fun runOnlyFirstInit(block: () -> Unit) {
        if (viewModel.firstOpen) block()
    }

    fun showSnackBar(
        targetView: View = requireView().findViewById(R.id.coordinatorLayout),
        message: String,
        type: SnackBarType
    ) {
        val color = when (type) {
            SnackBarType.SUCCESS -> android.R.color.holo_green_dark
            SnackBarType.ERROR -> android.R.color.holo_orange_dark
        }
        Snackbar.make(targetView, message, Snackbar.LENGTH_LONG).apply {
            setBackgroundTint(ContextCompat.getColor(requireContext(), color))
            setAction("Close") { dismiss() }
        }.show()
    }

    fun showProgress() {
        if (progressDialog == null) {
            runContextNotNull {
                progressDialog = Dialog(it).apply {
                    requestWindowFeature(Window.FEATURE_NO_TITLE)
                    setCancelable(false)
                    setContentView(R.layout.layout_loading_white)
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
            }
        }

        if (!progressDialog?.isShowing.orFalse()) {
            progressDialog?.show()
        }

    }

    fun dismissProgress() {
        if (progressDialog?.isShowing.orFalse()) {
            progressDialog?.dismiss()
        }
    }

}