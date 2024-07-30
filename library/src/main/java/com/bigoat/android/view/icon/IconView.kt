package com.bigoat.android.view.icon

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.bigoat.android.view.R

/**
 * 徽章控件
 */
class IconView : AppCompatTextView {
    var name: String = ""
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
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initAttrs(typedArray: TypedArray) {
        var nameStr = typedArray.getString(R.styleable.IconView_name)
        if (nameStr == null) {
            val nameInt = typedArray.getInt(R.styleable.IconView_name, -1)
            if (nameInt != -1) {
                nameStr = resources.getString(nameInt)
            }
        }

        nameStr?.let {
            name = it
            updateView()
        }
    }

    private fun updateView() {
        typeface =  TypefaceCache.typeFace
        text = name
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
