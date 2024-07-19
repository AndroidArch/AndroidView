package com.bigoat.android.view.tabbar

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bigoat.android.view.R
import com.bigoat.android.view.utils.ViewUtils
import com.bigoat.android.view.utils.ViewUtils.darkenColor

class TabbarView : LinearLayout {
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

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (childCount > 1) {

        }
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabbarView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initAttrs(typedArray: TypedArray) {

    }

}
