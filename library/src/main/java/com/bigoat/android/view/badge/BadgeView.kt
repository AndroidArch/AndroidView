package com.bigoat.android.view.badge

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.bigoat.android.view.R

class BadgeView : AppCompatTextView {
    private var isDot = false
    private var value: String? = null
    private var max = 0
    private var type = 0
    private var showZero = false
    private var bgColor = 0
    private var shape = SHAPE_CIRCLE
    private var numberType = NUMBER_OVERFLOW
    private var inverted = false

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
        setTextColor(Color.WHITE)


        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BadgeView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initAttrs(typedArray: TypedArray) {
        isDot = typedArray.getBoolean(R.styleable.BadgeView_badgeIsDot, false)
        value = typedArray.getString(R.styleable.BadgeView_badgeValue)
        if (value==null) value = text.toString()
        max = typedArray.getInt(R.styleable.BadgeView_badgeMax, 9999)
        type = typedArray.getInt(R.styleable.BadgeView_badgeType, 0)
        showZero = typedArray.getBoolean(R.styleable.BadgeView_badgeShowZero, false)
        bgColor = typedArray.getColor(R.styleable.BadgeView_badgeBgColor, resources.getColor(R.color.v_error))
        shape = typedArray.getInt(R.styleable.BadgeView_badgeShape, SHAPE_CIRCLE)
        numberType = typedArray.getInt(R.styleable.BadgeView_badgeNumberType, NUMBER_OVERFLOW)
        inverted = typedArray.getBoolean(R.styleable.BadgeView_badgeInverted, false)
        updateView()
    }



    private fun updateView() {
        val drawable = GradientDrawable()

        if (bgColor == -1)
            when(type) {
            0 -> {
                bgColor = resources.getColor(R.color.v_primary)
            }
            1-> {
                bgColor = resources.getColor(R.color.v_success)
            }
            2-> {
                bgColor = resources.getColor(R.color.v_error)
            }
            3-> {
                bgColor = resources.getColor(R.color.v_warning)
            }
            4-> {
                bgColor = resources.getColor(R.color.v_info)
            }
        }

        drawable.setColor(bgColor)

        setPadding((CORNER_RADII/2).toInt(), 0, (CORNER_RADII/2).toInt(), 0)

        if (isDot) {
            drawable.shape = GradientDrawable.OVAL
            text = ""
        } else {
            drawable.shape = GradientDrawable.RECTANGLE
            when(shape) {
                SHAPE_CIRCLE -> {
                    drawable.cornerRadii = floatArrayOf(CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII)
                }
                SHAPE_HORN -> {
                    drawable.cornerRadii = floatArrayOf(CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII, 0f, 0f)
                }
            }
        }


        gravity = Gravity.CENTER
        background = drawable
    }

    companion object {
        private const val CORNER_RADII = 20f
        const val NUMBER_OVERFLOW = 0
        const val NUMBER_ELLIPSIS = 1
        const val NUMBER_LIMIT = 2
        const val SHAPE_CIRCLE = 0
        const val SHAPE_HORN = 1
    }
}
