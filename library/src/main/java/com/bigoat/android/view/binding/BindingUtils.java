package com.bigoat.android.view.binding;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

public class BindingUtils {

    public static int dp2px(final float dp) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dp * (scale + 0.5));
    }

    public static int getColorByRes(Context context, @ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }
}
