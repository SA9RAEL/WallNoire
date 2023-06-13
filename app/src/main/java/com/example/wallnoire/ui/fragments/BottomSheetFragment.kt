package com.example.wallnoire.ui.fragments

import android.app.DownloadManager
import android.app.WallpaperManager
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.depapel.R
import com.example.depapel.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.imageview.ShapeableImageView
import java.io.File
import java.io.IOException

class BottomSheetFragment(private val wallUrl: String) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        initButtons()

        return binding.root
    }

    private fun initButtons() {
        with(binding) {
            downLoadFromNet.setOnClickListener {
                downloadImageFromWeb(wallUrl)
            }
            setAsBackground.setOnClickListener {
                setAsBackground(backGround)
            }
            setAsLockscreen.setOnClickListener {
                setAsBackground(lockScreen)
            }
        }
    }

    private fun downloadImageFromWeb(url: String) {
        try {
            val downloadManager =
                context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

            val downloadUri = Uri.parse(url)

            val request = DownloadManager.Request(downloadUri).apply {
                setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setMimeType("image/*")
                    .setAllowedOverRoaming(false)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setTitle("wool")
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_PICTURES,
                        File.separator + "wool" + ".jpg"
                    )
            }
            downloadManager.enqueue(request)
            Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Image Download Failed ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setAsBackground(lockOrBackGround: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                val wallpaperManager = WallpaperManager.getInstance(context)
                val image = activity?.findViewById<ShapeableImageView>(R.id.download_image_view)

                if (image?.drawable == null) {
                    Toast.makeText(context, "Wait to loading", Toast.LENGTH_SHORT).show()
                } else {
                    val bitmap = (image.drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap, null, true, lockOrBackGround)
                    Toast.makeText(context, "Done", Toast.LENGTH_LONG).show()
                }
            } catch (e: IOException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object {
        const val lockScreen = 2
        const val backGround = 1
    }

}