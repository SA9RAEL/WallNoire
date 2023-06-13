package com.example.wallnoire.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.depapel.R
import com.example.depapel.databinding.FragmentDownloadBinding
import com.example.wallnoire.utils.BlurHashDecoder
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DownloadFragment : Fragment() {

    private lateinit var binding: FragmentDownloadBinding
    private val args: DownloadFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadBinding.inflate(inflater, container, false)
        loadWall(args.imageData[0])
        bottomSheet()
        addCallBacks()
        return binding.root
    }

    private fun loadWall(url: String) {
        val blurHashDrawable = BlurHashDecoder.decode(args.imageData[1])
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(blurHashDrawable?.toDrawable(this.resources))
            .error(blurHashDrawable)
            .into(binding.downloadImageView)

        binding.constraintDownload.background = BitmapDrawable(this.resources, blurHashDrawable)
    }

    private fun addCallBacks() {
        binding.backButton.setOnClickListener {v->
            Navigation.findNavController(v).popBackStack()
        }
    }

    private fun bottomSheet() {
        val bottomSheetFragment = BottomSheetFragment(args.imageData[0])
        binding.downloadButton.setOnClickListener {
            bottomSheetFragment.show(requireActivity().supportFragmentManager, "bottomSheetDialog")
        }
    }

}