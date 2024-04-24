package com.bigoat.android.view.button

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
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bigoat.android.view.R
import com.bigoat.android.view.utils.ViewUtils
import com.bigoat.android.view.utils.ViewUtils.darkenColor

class ButtonView : CardView {
    // 插槽
    enum class Slots {
        DEF,
        ICON,
        LOADING
    }

    // 按钮类型
    enum class Type {
        PRIMARY,
        SUCCESS,
        WARNING,
        DANGER
    }

    // 按钮大小
    enum class Size {
        MINI,
        SMALL,
        NORMAL,
        LARGE
    }

    // 属性
    private var type = 0

    // 是否朴素
    private var plain = false

    // 是否细边框
    private var hairline = false

    // 是否禁用
    private var disabled = false

    // 是否加载中
    private var loading = false

    // 加载中文本
    private var loadingText: String? = null

    // 图标
    private var icon: Drawable? = null

    // 是否正方形
    private var square = false

    // 是否圆形
    private var round = false

    // 大小
    private var size = 0

    // 自定义颜色
    private var color = 0

    // 文本控件
    private var textView: TextView? = null

    // 正常背景
    private var normalDrawable: GradientDrawable? = null

    // 按下背景
    private var pressedDrawable: GradientDrawable? = null

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
            removeView(textView)
        }
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        textView = TextView(context)
        textView!!.tag = "default"
        textView!!.gravity = Gravity.CENTER
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        textView!!.layoutParams = layoutParams
        addView(textView)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ButtonView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initAttrs(typedArray: TypedArray) {
        val text = typedArray.getString(R.styleable.ButtonView_buttonText)
        textView!!.text = text
        type = typedArray.getInt(R.styleable.ButtonView_buttonType, 0)
        plain = typedArray.getBoolean(R.styleable.ButtonView_buttonPlain, false)
        hairline = typedArray.getBoolean(R.styleable.ButtonView_buttonHairline, false)
        disabled = typedArray.getBoolean(R.styleable.ButtonView_buttonDisabled, false)
        loading = typedArray.getBoolean(R.styleable.ButtonView_buttonLoading, false)
        loadingText = typedArray.getString(R.styleable.ButtonView_buttonLoadingText)
        square = typedArray.getBoolean(R.styleable.ButtonView_buttonSquare, false)
        round = typedArray.getBoolean(R.styleable.ButtonView_buttonRound, false)
        icon = typedArray.getDrawable(R.styleable.ButtonView_buttonIcon)
        size = typedArray.getInt(R.styleable.ButtonView_buttonSize, 2)
        color = ViewUtils.getTypedArrayColor(typedArray, R.styleable.ButtonView_buttonColor, -1)
        normalDrawable = GradientDrawable()
        normalDrawable!!.shape = GradientDrawable.RECTANGLE
        pressedDrawable = GradientDrawable()
        pressedDrawable!!.shape = GradientDrawable.RECTANGLE
        setType(type, color, plain, hairline)
        setSize(size)
        setShape(square, round, ROUNDED_CORNERS)
        isEnabled = !disabled
        background = normalDrawable
    }

    // 设置形状
    fun setShape(square: Boolean, round: Boolean, radius: Int) {
        var radius = radius
        this.square = square
        this.round = round
        if (!square) {
            radius = ViewUtils.dp2px(radius.toFloat())
            normalDrawable!!.cornerRadii = floatArrayOf(
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat()
            )
            pressedDrawable!!.cornerRadii = floatArrayOf(
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat()
            )
        }
        if (round) {
            normalDrawable!!.shape = GradientDrawable.OVAL
            pressedDrawable!!.shape = GradientDrawable.OVAL
        }
    }

    fun setType(type: Type) {
        setType(type.ordinal, color, plain, hairline)
    }

    fun setPlain(plain: Boolean) {
        setType(type, color, plain, hairline)
    }

    fun setColor(color: Int) {
        setType(type, color, plain, hairline)
    }

    fun setHairline(hairline: Boolean) {
        setType(type, color, plain, hairline)
    }

    @SuppressLint("ResourceAsColor")
    private fun setType(type: Int, color: Int, plain: Boolean, hairline: Boolean) {
        if (color != -1) {
            this.color = color
        } else {
            when (type) {
                1 -> this.color = resources.getColor(R.color.v_success)
                2 -> this.color = resources.getColor(R.color.v_warning)
                3 -> this.color = resources.getColor(R.color.v_error)
                else -> this.color = resources.getColor(R.color.v_primary)
            }
        }
        if (plain) {
            val width = if (hairline) 2 else 4
            normalDrawable!!.setStroke(width, this.color)
            normalDrawable!!.setColor(Color.TRANSPARENT)
            textView!!.setTextColor(this.color)
            pressedDrawable!!.setColor(this.color)
        } else {
            normalDrawable!!.setColor(this.color)
            pressedDrawable!!.setColor(darkenColor(this.color, DARKENING_RATIO))
        }
    }

    private fun setSize(size: Size) {
        setSize(size.ordinal)
    }

    private fun setSize(size: Int) {
        this.size = size
        when (size) {
            0 -> {
                textView!!.height =
                    resources.getDimensionPixelSize(R.dimen.element_button_size_mini)
                textView!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimensionPixelSize(R.dimen.element_font_size_xs).toFloat()
                )
            }

            1 -> {
                textView!!.height =
                    resources.getDimensionPixelSize(R.dimen.element_button_size_small)
                textView!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimensionPixelSize(R.dimen.element_font_size_sm).toFloat()
                )
            }

            3 -> {
                textView!!.height =
                    resources.getDimensionPixelSize(R.dimen.element_button_size_large)
                textView!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimensionPixelSize(R.dimen.element_font_size_lg).toFloat()
                )
            }

            else -> {
                textView!!.height =
                    resources.getDimensionPixelSize(R.dimen.element_button_size_normal)
                textView!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimensionPixelSize(R.dimen.element_font_size_md).toFloat()
                )
            }
        }
    }

    val text: String
        get() = textView!!.text.toString()

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        alpha = if (enabled) 1f else 0.5f
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (isEnabled) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    pressed(true)
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    pressed(false)
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun pressed(pressed: Boolean) {
        if (pressed) {
            background = pressedDrawable
            if (plain || hairline) {
                textView!!.setTextColor(Color.WHITE)
            }
        } else {
            background = normalDrawable
            callOnClick()
            if (plain || hairline) {
                textView!!.setTextColor(color)
            }
        }
    }

    companion object {
        private const val DARKENING_RATIO = 0.8f
        private const val ROUNDED_CORNERS = 4
    }
}
