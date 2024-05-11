package com.bigoat.android.view.binding

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.transition.Transition
import android.view.View
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget

class BackgroundBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("android:background")
        fun setBackgroundResource(view: View, resource: Int) {
            view.setBackgroundResource(resource)
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setBackgroundDrawable(view: View, drawable: Drawable) {
            view.background = drawable
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setBackgroundBitmap(view: View, bitmap: Bitmap) {
            view.background = BitmapDrawable(view.context.resources, bitmap)
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setBackgroundUri(view: View, uri: Uri) {
//            view.background = BitmapDrawable(view.context.resources, uri)
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setBackgroundUrl(view: View, url: String) {
        }
    }
}