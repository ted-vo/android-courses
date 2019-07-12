package dev.nxonxon.exampleview

import android.graphics.Bitmap

interface ImageLoaderCallback {

    fun onCompleted(bm: Bitmap)

    fun onFailed(e: Exception)
}