package com.bigoat.android.view.binding;



import static com.bigoat.android.view.binding.BindingUtils.dp2px;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.databinding.BindingAdapter;

import org.jetbrains.annotations.NotNull;

public final class ViewBinding {

    @BindingAdapter(value = {
            "backgroundColor",
            "backgroundRound",
            "backgroundCorner",
            "backgroundCornerTopLeft",
            "backgroundCornerTopRight",
            "backgroundCornerBottomRight",
            "backgroundCornerBottomLeft"
    }, requireAll = false)
    public static void load(@NotNull View view,
                            int color,
                            boolean round,
                            int corner,
                            int cornerTopLeft,
                            int cornerTopRight,
                            int cornerBottomRight,
                            int cornerBottomLeft) {
        GradientDrawable drawable = new GradientDrawable();
        if (color == 0) {
            Drawable colorDrawable = view.getBackground();
            if (colorDrawable instanceof ColorDrawable) {
                color = ((ColorDrawable) colorDrawable).getColor();
            }
        }
        drawable.setColor(color);

        drawable.setShape(round ? GradientDrawable.OVAL : GradientDrawable.RECTANGLE);

        if (cornerTopLeft != 0 || cornerTopRight != 0 || cornerBottomLeft != 0 || cornerBottomRight != 0) {
            drawable.setCornerRadii(new float[]{
                    dp2px(cornerTopLeft), dp2px(cornerTopLeft),
                    dp2px(cornerTopRight), dp2px(cornerTopRight),
                    dp2px(cornerBottomLeft), dp2px(cornerBottomLeft),
                    dp2px(cornerBottomRight), dp2px(cornerBottomRight)});
        }

        if (corner != 0) {
            int radius = dp2px(corner);
            drawable.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        }

        view.setBackground(drawable);
    }

    @BindingAdapter(value = {"borderColor", "borderWidth", "borderCorner"}, requireAll = false)
    public static void setBorder(View view, int borderColor, int borderWidth, int borderCorner) {
        Drawable background = view.getBackground();
        int backgroundColor = Color.TRANSPARENT;
        if (background instanceof ColorDrawable) {
            backgroundColor = ((ColorDrawable) background).getColor();
        }

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);

        drawable.setCornerRadius(dp2px(borderCorner));
        drawable.setColor(backgroundColor);
        drawable.setStroke(dp2px(borderWidth), borderColor);

        view.setBackground(drawable);
    }

    @BindingAdapter(value = {
            "lineColor",
            "lineDashWidth",
            "lineDashGap",
    }, requireAll = false)
    public static void loadLine(@NotNull View view,
                            int color,
                                int dashWidth,
                                int dashGap) {

        if (dashGap== 0) dashGap = dashWidth;

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.LINE);
        drawable.setStroke(2, color, dp2px(dashWidth), dp2px(dashGap));
        view.setBackground(drawable);
    }


}
