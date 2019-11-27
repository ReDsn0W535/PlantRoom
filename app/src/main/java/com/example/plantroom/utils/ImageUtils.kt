package com.example.plantroom.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

class ImageUtils {
    companion object {
        fun ByteArray.toBitmap(): Bitmap =
            BitmapFactory.decodeByteArray(this, 0, this.size)
    }
}