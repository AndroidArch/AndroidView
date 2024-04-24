package com.bigoat.android.view.image

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bigoat.android.view.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.net.URL

class ImageView : AppCompatImageView {
    private var imageSrc: String? = null
    private var imageHolder: Drawable? = null
    private var imageError: Drawable? = null
    private var imageRound = false
    private var imageCorner = 0
    private var imageCornerTopLeft = 0
    private var imageCornerTopRight = 0
    private var imageCornerBottomRight = 0
    private var imageCornerBottomLeft = 0

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initAttrs(typedArray: TypedArray) {
        imageSrc = typedArray.getString(R.styleable.ImageView_imageSrc)
        imageHolder = typedArray.getDrawable(R.styleable.ImageView_imageHolder)
        imageError = typedArray.getDrawable(R.styleable.ImageView_imageError)
        imageRound = typedArray.getBoolean(R.styleable.ImageView_imageRound, false)
        imageCorner = typedArray.getInt(R.styleable.ImageView_imageCorner, -1)
        imageCornerTopLeft = typedArray.getInt(R.styleable.ImageView_imageCornerTopLeft, -1)
        imageCornerTopRight = typedArray.getInt(R.styleable.ImageView_imageCornerTopRight, -1)
        imageCornerBottomRight =
            typedArray.getInt(R.styleable.ImageView_imageCornerBottomRight, -1)
        imageCornerBottomLeft = typedArray.getInt(R.styleable.ImageView_imageCornerBottomLeft, -1)
        val index = R.styleable.ImageView_imageSrc
        val imageSrc = typedArray.getString(index)
        if (imageSrc != null) {
            if (imageSrc.startsWith("#")) {
                val colorValue = typedArray.getColor(index, -1)
                if (colorValue != -1) {
                    val drawable: Drawable = ColorDrawable(colorValue)
                    load(
                        this,
                        drawable,
                        imageHolder,
                        imageError,
                        imageRound,
                        imageCorner,
                        imageCornerTopLeft,
                        imageCornerTopRight,
                        imageCornerBottomRight,
                        imageCornerBottomLeft
                    )
                }
            } else if (imageSrc.startsWith("res/")) {
                val drawable = typedArray.getDrawable(index)
                load(
                    this,
                    drawable,
                    imageHolder,
                    imageError,
                    imageRound,
                    imageCorner,
                    imageCornerTopLeft,
                    imageCornerTopRight,
                    imageCornerBottomRight,
                    imageCornerBottomLeft
                )
            } else {
                load(
                    this,
                    imageSrc,
                    imageHolder,
                    imageError,
                    imageRound,
                    imageCorner,
                    imageCornerTopLeft,
                    imageCornerTopRight,
                    imageCornerBottomRight,
                    imageCornerBottomLeft
                )
            }
        }
    }

    companion object {
        @JvmStatic
        @SuppressLint("CheckResult")
        @BindingAdapter(
            value = ["imageSrc",  // 源图片
                "imageHolder",  // 占位图
                "imageError",  // 错误图
                "imageRound",  // 圆形图片
                "imageCorner",  // 圆角大小
                "imageCornerTopLeft",  // 圆角左上角大小
                "imageCornerTopRight",  // 圆角右上角大小
                "imageCornerBottomRight",  // 圆角左下角大小
                "imageCornerBottomLeft" // 圆角右下角大小
            ], requireAll = false
        )
        fun load(
            view: ImageView,
            src: Any?,
            holder: Drawable?,
            error: Drawable?,
            round: Boolean,
            imageCorner: Int,
            imageCornerTopLeft: Int,
            imageCornerTopRight: Int,
            imageCornerBottomRight: Int,
            imageCornerBottomLeft: Int
        ) {
            val options = RequestOptions()
            if (holder != null) options.placeholder(holder)
            if (error != null) options.error(error)
            if (imageCornerBottomLeft > 0 || imageCornerBottomRight > 0 || imageCornerTopLeft > 0 || imageCornerTopRight > 0) {
                options.transform(
                    GranularRoundedCorners(
                        imageCornerTopLeft.toFloat(),
                        imageCornerTopRight.toFloat(),
                        imageCornerBottomRight.toFloat(),
                        imageCornerBottomLeft.toFloat()
                    )
                )
            }
            if (imageCorner > 0) {
                options.transform(RoundedCorners(imageCorner))
            }
            if (round) {
                options.circleCrop()
            }
            load(view, src, options)
        }

        fun load(view: ImageView, src: Any?) {
            load(view, src, null, null, false, 0, 0, 0, 0, 0)
        }

        fun load(view: ImageView, src: Any?, error: Drawable?) {
            load(view, src, null, error, false, 0, 0, 0, 0, 0)
        }

        fun load(view: ImageView, src: Any?, holder: Drawable?, error: Drawable?) {
            load(view, src, holder, error, false, 0, 0, 0, 0, 0)
        }

        private fun load(view: ImageView, src: Any?, options: RequestOptions) {
            when (src) {
                is Bitmap -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }

                is Drawable -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }

                is String -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }

                is Uri -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }

                is File -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }

                is URL -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }

                is Int -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }

                else -> {
                    Glide.with(view).load(src).apply(options).into(view)
                }
            }
        }
    }
}
