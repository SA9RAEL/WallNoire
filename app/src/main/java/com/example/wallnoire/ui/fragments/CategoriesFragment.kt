package com.example.wallnoire.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.depapel.R
import com.example.depapel.databinding.FragmentCategoriesBinding
import com.example.wallnoire.WallApplication
import com.example.wallnoire.localData.model.TopicItem
import com.example.wallnoire.recyclerview.CategoriesAdapter
import com.example.wallnoire.recyclerview.CategoryInteractionListener
import com.example.wallnoire.ui.fragments.base.BaseFragment
import com.example.wallnoire.utils.ApiListCategory


class CategoriesFragment : BaseFragment(R.layout.fragment_categories), CategoryInteractionListener {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var recyclerViewAdapter: CategoriesAdapter

    override fun onAttach(context: Context) {
        (context.applicationContext as WallApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        recyclerAdapter()

        return binding.root
    }

    private fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 2)
        recyclerViewAdapter = CategoriesAdapter(ApiListCategory.list, this)
        binding.categoriesRecyclerView.layoutManager = layoutManager
        binding.categoriesRecyclerView.adapter = recyclerViewAdapter
    }

    override fun onClickCategory(topicItem: TopicItem, view: View) {
        val action =
            CategoriesFragmentDirections.actionCategoriesFragmentToSpecificCategoryFragment(topicItem.title)
        Navigation.findNavController(view).navigate(action)
    }
}