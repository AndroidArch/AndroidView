package com.bigoat.android.view.sidebar

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity.CENTER
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import com.bigoat.android.view.R
import com.bigoat.android.view.utils.ViewUtils

class SidebarItemView : LinearLayout {
    private var iconPosition = ICON_LEFT
    private val textView: TextView = TextView(context)
    private var text: String? = null
    private var textColor = 0
    private var selectTextColor = 0
    private var textSize = 0
    private var iconView: ImageView? = null
    private var icon: Drawable? = null
    private var selectIcon: Drawable? = null
    private var iconWidth = 0
    private var iconHeight = 0
    private var selected = false
    private var spacing = 0

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
        gravity = CENTER

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SidebarItemView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun initAttrs(typedArray: TypedArray) {

        // 根据属性设置值
        iconPosition = typedArray.getInt(R.styleable.SidebarItemView_sidebarItemModel, ICON_LEFT)
        spacing = typedArray.getDimension(R.styleable.SidebarItemView_sidebarItemSpacing, 0f).toInt()
        icon = typedArray.getDrawable(R.styleable.SidebarItemView_sidebarItemIcon)
        selectIcon = typedArray.getDrawable(R.styleable.SidebarItemView_sidebarItemSelectIcon)
        iconWidth = typedArray.getDimension(R.styleable.SidebarItemView_sidebarItemIconWidth, ViewGroup.LayoutParams.WRAP_CONTENT.toFloat()).toInt()
        iconHeight = typedArray.getDimension(R.styleable.SidebarItemView_sidebarItemIconHeight, iconWidth.toFloat()).toInt()
        text = typedArray.getString(R.styleable.SidebarItemView_sidebarItemText)
        textSize = typedArray.getDimension(R.styleable.SidebarItemView_sidebarItemTextSize, 14f).toInt()
        textColor = typedArray.getColor(R.styleable.SidebarItemView_sidebarItemTextColor, Color.WHITE)
        selectTextColor = typedArray.getColor(R.styleable.SidebarItemView_sidebarItemTextSelectColor, Color.WHITE)

        updateView()
    }

    private fun updateView() {
        removeAllViews()
        when (iconPosition) {
            ICON_LEFT -> {
                orientation = HORIZONTAL
                updateIcon()
                updateText()
            }
            ICON_TOP -> {
                updateIcon()
                updateText()
                orientation = VERTICAL
            }
            ICON_RIGHT -> {
                updateText()
                updateIcon()
                orientation = HORIZONTAL
            }
            ICON_BOTTOM -> {
                updateText()
                updateIcon()
                orientation = VERTICAL
            }
        }
    }

    private fun updateIcon() {
        if (icon == null) {
            return
        }
        if (iconView == null) {
            iconView = ImageView(context)
        }
        // 根据选中状态设置图标
        iconView!!.setImageDrawable(if (selected) selectIcon else icon)
        iconView!!.layoutParams = LayoutParams(iconWidth, iconHeight)

        addView(iconView)
    }

    private fun updateText() {
        textView.text = text
        textView.setTextColor(if (selected) selectTextColor else textColor)
        textView.layoutParams = LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            ViewUtils.dp2px(textSize.toFloat()).toFloat()
        )
        val layoutParams = textView.layoutParams as MarginLayoutParams
        when(iconPosition) {
            ICON_LEFT -> {
                layoutParams.marginStart = spacing
            }
            ICON_TOP -> {
                layoutParams.topMargin = spacing
            }
            ICON_RIGHT -> {
                layoutParams.marginEnd = spacing
            }
            ICON_BOTTOM -> {
                layoutParams.bottomMargin = spacing
            }
        }

        addView(textView)
    }

    companion object {
        const val ICON_LEFT = 0
        const val ICON_TOP = 1
        const val ICON_RIGHT = 2
        const val ICON_BOTTOM = 3
    }
}
