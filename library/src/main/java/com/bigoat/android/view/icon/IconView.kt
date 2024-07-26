package com.bigoat.android.view.icon

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.bigoat.android.view.R
import java.lang.reflect.Field

/**
 * 徽章控件
 */
class IconView : AppCompatTextView {
    var name: Int = -1
        set(value) {
            if (value != -1) {
                field = value
                setText(value)
            }
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
        typeface = TYPE_FACE
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconView)
        try {
            initAttrs(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun initAttrs(typedArray: TypedArray) {
        name = typedArray.getInt(R.styleable.IconView_name, -1)
        if (name != -1) setText(name)

        updateView()
    }

    private fun updateView() {

    }


    companion object {
        val TYPE_FACE: Typeface
            get() = runCatching {
                IconInitializer.context?.let {
                    ResourcesCompat.getFont(
                        it,
                        R.font.faw_solid_5_13_3
                    )
                }
            }.getOrNull() ?: Typeface.DEFAULT
    }
}
