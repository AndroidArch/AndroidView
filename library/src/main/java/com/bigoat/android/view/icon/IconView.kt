package com.bigoat.android.view.icon

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.bigoat.android.view.R
import com.bigoat.android.view.utils.ViewUtils

/**
 * 图标控件
 */
class IconView : AppCompatTextView {
    var name: String = ""
        set(value) {
            field = value
            updateView()
        }

    var color: Int = resources.getColor(R.color.v_primary)
        set(value) {
            field = value
            updateView()
        }

    var size: Float = resources.getDimension(R.dimen.v_font_size)
        set(value) {
            field = value
            updateView()
        }

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
        typeface = TypefaceCache.typeFace

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initAttrs(typedArray: TypedArray) {
        name = ViewUtils.getTypedArrayString(typedArray, R.styleable.IconView_name, "")
        color = ViewUtils.getTypedArrayColor(typedArray, R.styleable.IconView_color, R.color.v_primary)
        size = ViewUtils.getTypedArrayFontSize(typedArray, R.styleable.IconView_size, resources.getDimension(R.dimen.v_font_size))

        updateView()
    }

    private fun updateView() {
        text = name
        setTextColor(color)
        textSize = size
    }

    fun setName(name: Int) {
        this.name = resources.getString(name)
    }
}

object TypefaceCache {
    val typeFace: Typeface by lazy {
        ResourcesCompat.getFont(IconInitializer.context!!, R.font.uview)
            ?: Typeface.DEFAULT
    }

}
