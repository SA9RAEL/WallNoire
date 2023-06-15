package com.example.wallnoire.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.Manifest
import android.content.pm.PackageManager
import com.example.wallnoire.data.enum.PermissionType

object PermissionManager {

    fun checkPermission(context: Context, type: PermissionType): Boolean {
        return when (type) {
            PermissionType.WRITE_EXTERNAL_STORAGE -> checkWriteExternalStoragePermission(context)
        }
    }

    fun requestPermission(fragment: Fragment, type: PermissionType) {
        when (type) {
            PermissionType.WRITE_EXTERNAL_STORAGE -> requestWriteExternalStoragePermission(fragment)
        }
    }

    /*
        WRITE_EXTERNAL_STORAGE
     */
    private fun checkWriteExternalStoragePermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestWriteExternalStoragePermission(fragment: Fragment) {
        fragment.requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            RC_WRITE_EXTERNAL_STORAGE)
    }


    const val RC_WRITE_EXTERNAL_STORAGE = 10001
}