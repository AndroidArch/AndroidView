package com.bigoat.android.view.badge

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.bigoat.android.view.R

class BadgeView : AppCompatTextView {
    private var isDot = false
    private var value: String? = null
    private var max = 0
    private var type = 0
    private var showZero = false
    private var bgColor = 0
    private var mShape = SHAPE_CIRCLE
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

    private fun initAttrs(typedArray: TypedArray) {
        isDot = typedArray.getBoolean(R.styleable.BadgeView_badgeIsDot, false)
        value = typedArray.getString(R.styleable.BadgeView_badgeValue) ?: text.toString()
        max = typedArray.getInt(R.styleable.BadgeView_badgeMax, 999)
        type = typedArray.getInt(R.styleable.BadgeView_badgeType, 2)
        showZero = typedArray.getBoolean(R.styleable.BadgeView_badgeShowZero, false)
        bgColor = when (type) {
            0 -> ContextCompat.getColor(context, R.color.v_primary)
            1 -> ContextCompat.getColor(context, R.color.v_success)
            2 -> ContextCompat.getColor(context, R.color.v_error)
            3 -> ContextCompat.getColor(context, R.color.v_warning)
            4 -> ContextCompat.getColor(context, R.color.v_info)
            else -> -1
        }
        mShape = typedArray.getInt(R.styleable.BadgeView_badgeShape, SHAPE_CIRCLE)
        numberType = typedArray.getInt(R.styleable.BadgeView_badgeNumberType, NUMBER_OVERFLOW)
        inverted = typedArray.getBoolean(R.styleable.BadgeView_badgeInverted, false)
        updateView()
    }

    private fun updateView() {
        if (inverted) {
            setTextColor(bgColor)
        } else {
            val drawable = GradientDrawable().apply {
                setColor(bgColor)
                cornerRadii = floatArrayOf(
                    CORNER_RADII, CORNER_RADII, CORNER_RADII, CORNER_RADII,
                    CORNER_RADII, CORNER_RADII,
                    if (mShape == SHAPE_HORN) 0f else CORNER_RADII,
                    if (mShape == SHAPE_HORN) 0f else CORNER_RADII
                )
                shape = if (isDot) GradientDrawable.OVAL else GradientDrawable.RECTANGLE
            }

            if (!isDot) {
                setPadding(PADDING, 0, PADDING, 0)
                text = when (numberType) {
                    NUMBER_OVERFLOW -> if (value?.toInt() ?: 0 > max) "$max+" else value
                    NUMBER_ELLIPSIS -> if (value?.toInt() ?: 0 > max) "$max..." else value
                    NUMBER_LIMIT -> value?.toFloat()?.let { v ->
                        if (v > 1000) {
                            val k = v / 1000
                            val w = v / 10000
                            if (k < 10) {
                                String.format("%.2f", k) + "k"
                            } else {
                                String.format("%.2f", w) + "w"
                            }
                        } else {
                            value
                        }
                    } ?: ""

                    else -> {
                        if (showZero) value else ""}
                }
            } else {
                text = ""
            }

            gravity = Gravity.CENTER
            background = drawable
        }
    }


    companion object {
        private const val CORNER_RADII = 20f
        private const val PADDING = CORNER_RADII.toInt()
        const val NUMBER_OVERFLOW = 0
        const val NUMBER_ELLIPSIS = 1
        const val NUMBER_LIMIT = 2
        const val SHAPE_CIRCLE = 0
        const val SHAPE_HORN = 1
    }
}
