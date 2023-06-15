package com.example.wallnoire.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.wallnoire.R
import com.example.wallnoire.utils.State

class BaseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var loadingView: View? = null
    private var emptyView: View? = null
    private var errorView: View? = null

    private var imageResIdOfEmptyView: Int? = null
    private var textOfEmptyView: String? = null
    private var imageViewOfEmptyView: ImageView? = null
    private var textViewOfEmptyView: TextView? = null

    private var imageResIdOfErrorView: Int? = null
    private var textOfErrorView: String? = null
    private var imageViewOfErrorView: ImageView? = null
    private var textViewOfErrorView: TextView? = null

    init {
        val inflater = LayoutInflater.from(getContext())

        attrs.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.BaseView)

            val loadingViewResId =
                a.getResourceId(R.styleable.BaseView_loadingView, R.layout.layout_loading)
            if (loadingViewResId != -1) {
                val inflatedLoadingView = inflater.inflate(loadingViewResId, this, false)
                this.loadingView = inflatedLoadingView
            }

            val emptyViewResId =
                a.getResourceId(R.styleable.BaseView_emptyView, R.layout.layout_empty)
            if (emptyViewResId != -1) {
                val inflatedEmptyView = inflater.inflate(emptyViewResId, this, false)
                this.emptyView = inflatedEmptyView

                if (emptyViewResId == R.layout.layout_empty) {
                    this.imageResIdOfEmptyView = a.getResourceId(
                        R.styleable.BaseView_imageOfEmptyView,
                        R.drawable.ic_mood_bad_black_24dp
                    )
                    this.textOfEmptyView =
                        a.getString(R.styleable.BaseView_textOfEmptyView) ?: "Something went wrong."

                    this.imageViewOfEmptyView = emptyView?.findViewById(R.id.image)
                    this.textViewOfEmptyView = emptyView?.findViewById(R.id.text)

                    initEmptyViewFields()
                }
            }


            val errorViewResId =
                a.getResourceId(R.styleable.BaseView_errorView, R.layout.layout_error)
            if (errorViewResId != -1) {
                val inflatedErrorView = inflater.inflate(errorViewResId, this, false)
                this.errorView = inflatedErrorView

                if (errorViewResId == R.layout.layout_error) {
                    this.imageResIdOfErrorView = a.getResourceId(
                        R.styleable.BaseView_imageOfErrorView,
                        R.drawable.ic_error_outline_black_24dp
                    )
                    this.textOfErrorView =
                        a.getString(R.styleable.BaseView_textOfErrorView) ?: "Something went wrong."

                    this.imageViewOfErrorView = errorView?.findViewById(R.id.image)
                    this.textViewOfErrorView = errorView?.findViewById(R.id.text)

                    initErrorViewFields(
                        a.getBoolean(
                            R.styleable.BaseView_buttonVisibilityOfErrorView,
                            true
                        )
                    )
                }
            }

            a.recycle()
        }
    }


    private fun initEmptyViewFields() {
        imageViewOfEmptyView?.setImageResource(imageResIdOfEmptyView!!)
        textViewOfEmptyView?.text = textOfEmptyView
    }


    private fun initErrorViewFields(buttonVisibility: Boolean) {
        imageViewOfErrorView?.setImageResource(imageResIdOfErrorView!!)
        textViewOfErrorView?.text = textOfErrorView
    }

    private fun showLoading() {
        if (loadingView?.parent == null) this.addView(loadingView, loadingView?.layoutParams)
        if (emptyView?.parent == this) this.removeView(emptyView)
        if (errorView?.parent == this) this.removeView(errorView)
        setOtherChildVisibility(false, loadingView)
    }

    private fun showEmpty() {
        if (loadingView?.parent == this) this.removeView(loadingView)
        if (emptyView?.parent == null) this.addView(emptyView, emptyView?.layoutParams)
        setOtherChildVisibility(false, emptyView)
    }

    private fun showError() {
        if (loadingView?.parent == this) this.removeView(loadingView)
        if (errorView?.parent == null) this.addView(errorView, errorView?.layoutParams)
        setOtherChildVisibility(false, errorView)
    }

    private fun showContent() {
        if (loadingView?.parent == this) this.removeView(loadingView)
        setOtherChildVisibility(true, null)
    }

    fun setState(state: State?) {
        when (state) {
            State.LOADING -> showLoading()
            State.SUCCESS -> showContent()
            State.EMPTY -> showEmpty()
            State.ERROR -> {
                showError()
            }

            else -> {
            }
        }
    }


    private fun setOtherChildVisibility(state: Boolean, view: View?) {
        for (i in 0 until this.childCount)
            if (getChildAt(i) != view)
                getChildAt(i).isVisible = state
    }

    class Builder {
        private lateinit var view: BaseView
        private var state: State? = null

        fun view(view: BaseView): Builder {
            this.view = view
            return this
        }

        fun state(state: State?): Builder {
            this.state = state
            return this
        }

        fun build() {
            view.setState(state)
        }
    }

}