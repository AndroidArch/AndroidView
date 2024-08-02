package com.bigoat.android.view.navbar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bigoat.android.view.R
import com.bigoat.android.view.icon.IconView

/**
 * NavBar控件
 */
class NavbarView : FrameLayout {
    private lateinit var leftView: IconView
    private lateinit var titleView: TextView
    private lateinit var rightView: IconView

    private var leftListener = OnClickListener { if (autoBack) finishActivity() }
    private var rightListener = OnClickListener { }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        leftView = IconView(context).apply {
            setOnClickListener(leftListener)
        }

        titleView = TextView(context).apply {
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, R.color.v_primary))
        }

        rightView = IconView(context).apply {
            setOnClickListener(rightListener)
        }

        addView(leftView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply {
            gravity = Gravity.START
        })

        addView(titleView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply {
                gravity = Gravity.CENTER
            })

        addView(rightView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply {
                gravity = Gravity.END
            })

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NavbarView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }

    }

    var autoBack: Boolean = true

    @SuppressLint("ResourceType")
    private fun initAttrs(typedArray: TypedArray) {
        autoBack = typedArray.getBoolean(R.styleable.NavbarView_navbarAutoBack, true)

        typedArray.getString(R.styleable.NavbarView_navbarLeftText)?.let { text ->
            leftView.text = text
        }
        typedArray.getColor(R.styleable.NavbarView_navbarLeftIconColor, R.color.v_main_color).let { color ->
            leftView.setTextColor(color)
        }
        typedArray.getDimensionPixelSize(R.styleable.NavbarView_navbarLeftIconColor, R.color.v_main_color).let { color ->
            leftView.setTextColor(color)
        }

        typedArray.getString(R.styleable.NavBarView_title).let { text ->
            titleView.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
            titleView.text = text
        }

        typedArray.getString(R.styleable.NavBarView_rightText).let { text ->
            rightView.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
            rightView.text = text
        }

        updateView()
    }

    private fun updateView() {

    }

    private fun finishActivity() {
        (context as? Activity)?.finish()
    }

    companion object {}

}
