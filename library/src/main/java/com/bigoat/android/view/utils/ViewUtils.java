package com.bigoat.android.view.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.StyleableRes;

/**
 * ViewUtils 是一个工具类，提供了一些视图相关的操作。
 */
public class ViewUtils {

    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Value of px to value of dp.
     *
     * @param pxValue The value of px.
     * @return value of dp
     */
    public static int px2dp(final float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Value of sp to value of px.
     *
     * @param spValue The value of sp.
     * @return value of px
     */
    public static int sp2px(final float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * Value of px to value of sp.
     *
     * @param pxValue The value of px.
     * @return value of sp
     */
    public static int px2sp(final float pxValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 按给定比例暗化颜色。
     *
     * @param color 要暗化的颜色。
     * @param ratio 暗化颜色的比例。这应该是一个介于 0 和 1 之间的浮点数，其中 1 使颜色保持不变，0 使颜色完全变黑。
     * @return 暗化的颜色。
     */
    public static int darkenColor(int color, float ratio) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        // 减小亮度
        hsv[2] *= ratio;
        return Color.HSVToColor(hsv);
    }

    public static String getTypedArrayString(TypedArray typedArray, @StyleableRes int index, String defValue) {
        if (typedArray !=null) {
            String value = typedArray.getString(index);
            if (value != null) {
                return value;
            }
        }
        return defValue;
    }

    public static int getTypedArrayColor(TypedArray typedArray, @StyleableRes int index, int defValue) {
        if (typedArray !=null) {
            try {
                int color = typedArray.getColor(index, -1);
                if (color == -1) {
                    String value = typedArray.getString(index);
                    if (value != null) {
                        color = Color.parseColor(value);
                    }
                }

                return color;

            } catch (Exception e) {
                return defValue;
            }

        }
        return defValue;
    }

    public static int getColor(Context context, @ColorRes int index) {
        return context.getResources().getColor(index);
    }

    public static TextView copy(TextView textView) {
        TextView copy = new TextView(textView.getContext());
        copy.setText(textView.getText());
        copy.setTextSize(textView.getTextSize());
        copy.setTextColor(textView.getCurrentTextColor());
        copy.setTypeface(textView.getTypeface());
        copy.setGravity(textView.getGravity());
        copy.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), textView.getPaddingBottom());
        copy.setLayoutParams(textView.getLayoutParams());
        copy.setBackground(textView.getBackground());
        copy.setCompoundDrawablePadding(textView.getCompoundDrawablePadding());
        copy.setCompoundDrawablesWithIntrinsicBounds(textView.getCompoundDrawables()[0], textView.getCompoundDrawables()[1], textView.getCompoundDrawables()[2], textView.getCompoundDrawables()[3]);
        return copy;
    }
}