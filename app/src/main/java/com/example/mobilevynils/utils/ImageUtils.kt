package com.example.mobilevynils.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class ImageUtils {

        fun getBitmapFromUrl(urlString: String): Bitmap? {
            return try {
                val url = URL(urlString)
                BitmapFactory.decodeStream(url.openConnection().getInputStream())
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

}
