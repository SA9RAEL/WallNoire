package com.example.wallnoire.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.depapel.R
import com.example.depapel.databinding.FragmentSpecificCategoryBinding
import com.example.wallnoire.WallApplication
import com.example.wallnoire.localData.model.PreviewPhoto
import com.example.wallnoire.recyclerview.RecyclerViewAdapter
import com.example.wallnoire.recyclerview.WallInteractionListener
import com.example.wallnoire.ui.fragments.base.BaseFragment
import com.example.wallnoire.viewmodels.CategoriesViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SpecificCategoryFragment : BaseFragment(R.layout.fragment_specific_category),
    WallInteractionListener {

    @Inject
    lateinit var categoriesViewModelFactory: CategoriesViewModel.CategoriesViewModelFactory

    private val viewModel: CategoriesViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return categoriesViewModelFactory.create(1) as T
            }
        }
    }
    private val args: SpecificCategoryFragmentArgs by navArgs()
    private var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(this)
    private lateinit var binding: FragmentSpecificCategoryBinding


    override fun onAttach(context: Context) {
        (context.applicationContext as WallApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecificCategoryBinding.inflate(inflater)
        initViewModel()
        recyclerAdapter()
        categoryName()
        callBack()
        return binding.root
    }

    private fun initViewModel() {


        viewModel.data.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        val layoutManager = GridLayoutManager(context, 3)
//        binding.wallCategoryRecyclerView.layoutManager = layoutManager
//        binding.wallCategoryRecyclerView.adapter = recyclerViewAdapter
//
//        viewModel.data.observe(viewLifecycleOwner) {
//            lifecycleScope.launch {
//                recyclerViewAdapter.submitData(it)
//            }
//        }
//
//    }

    private fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallCategoryRecyclerView.layoutManager = layoutManager
        binding.wallCategoryRecyclerView.adapter =
            recyclerViewAdapter


        recyclerViewAdapter.addLoadStateListener { loadState ->
            binding.wallCategoryRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            binding.wallCategoryRecyclerView.isVisible =
                loadState.source.refresh is LoadState.Loading
            binding.wallCategoryRecyclerView.isVisible =
                loadState.source.refresh is LoadState.Error
            handelError(loadState)
        }
        binding.wallCategoryRecyclerView.setOnClickListener {
            recyclerViewAdapter.retry()
        }

    }

    private fun handelError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
        }
    }


    override fun onClickItem(data: PreviewPhoto, view: View) {
        val imageData = arrayOf(data.urls, data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(
                SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToDownloadFragment(
                    imageData
                )
            )
    }

    private fun callBack() {
        binding.categoryName.onRightDrawableClicked {
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun categoryName() {
        binding.categoryName.text = args.categoryName
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun TextView.onRightDrawableClicked(onClicked: (view: TextView) -> Unit) {
        this.setOnTouchListener { v, event ->
            var hasConsumed = false
            if (v is TextView) {
                if (event.x >= v.width - v.totalPaddingRight) {
                    if (event.action == MotionEvent.ACTION_UP) {
                        onClicked(this)
                    }
                    hasConsumed = true
                }
            }
            hasConsumed
        }
    }
}