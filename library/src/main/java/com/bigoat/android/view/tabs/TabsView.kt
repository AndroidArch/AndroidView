package com.bigoat.android.view.tabs

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bigoat.android.view.R
import com.google.android.material.tabs.TabLayout

class TabsView : TabLayout {
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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabsView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initAttrs(typedArray: TypedArray) {
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

        }

    }
}
