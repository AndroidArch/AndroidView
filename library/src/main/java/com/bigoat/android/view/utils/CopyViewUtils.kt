package com.bigoat.android.view.utils

import android.content.Context
import android.widget.TextView

class CopyViewUtils {


    companion object {
        @JvmStatic
        fun copyTextView(context: Context, textView: TextView): TextView {
            return TextView(context).apply {
                text = textView.text
                background = textView.background
                layoutParams = textView.layoutParams
                setPadding(textView.paddingLeft, textView.paddingTop, textView.paddingRight, textView.paddingBottom)
                compoundDrawablePadding = textView.compoundDrawablePadding
                setCompoundDrawables(textView.compoundDrawables[0], textView.compoundDrawables[1], textView.compoundDrawables[2], textView.compoundDrawables[3])
            }
        }
    }
}