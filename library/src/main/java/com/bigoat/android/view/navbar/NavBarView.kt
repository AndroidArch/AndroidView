package com.bigoat.android.view.navbar

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
class NavBarView : FrameLayout {
    private var value: String? = null

    lateinit var leftView: IconView
    lateinit var titleView: TextView
    lateinit var rightView: TextView

    private var leftListener: OnClickListener = OnClickListener { finishActivity() }
    private var rightListener: OnClickListener = OnClickListener { }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        leftView = IconView(context).apply {
            setName(R.string.icon_chevron_backward)
            gravity = Gravity.CENTER
            textSize = resources.getDimension(R.dimen.v_font_size_md)
            setTextColor(ContextCompat.getColor(context, R.color.v_primary))
            setOnClickListener(leftListener)
        }

        titleView = TextView(context).apply {
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, R.color.v_primary))
        }

        rightView = TextView(context).apply {
            gravity = Gravity.CENTER
            setTextColor(ContextCompat.getColor(context, R.color.v_primary))
            setOnClickListener(rightListener)
        }

        addView(leftView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply {
            gravity = Gravity.LEFT
        })
        addView(titleView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply {
                gravity = Gravity.CENTER
            })
        addView(rightView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT).apply {
                gravity = Gravity.RIGHT
            })

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NavBarView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }

    }

    private fun initAttrs(typedArray: TypedArray) {
        typedArray.getString(R.styleable.NavBarView_leftText).let { text ->
//            leftView.visibility = if (text.isNullOrEmpty()) View.GONE else View.VISIBLE
//            leftView.text = text
        }

        typedArray.getString(R.styleable.NavBarView_titleText).let { text ->
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
