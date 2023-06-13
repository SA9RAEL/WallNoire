package com.example.wallnoire.utils

import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import java.io.File

class SingleMediaScanner(private val context: Context, private val file: File) :
    MediaScannerConnection.MediaScannerConnectionClient {

    private var msc: MediaScannerConnection? = null

    init {
        msc = MediaScannerConnection(context, this)
    }

    override fun onScanCompleted(path: String?, uri: Uri?) {
        msc?.disconnect()
    }

    override fun onMediaScannerConnected() {
        msc?.scanFile(file.absolutePath, null)
    }
}