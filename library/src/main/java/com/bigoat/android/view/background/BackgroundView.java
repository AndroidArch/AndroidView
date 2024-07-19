package com.bigoat.android.view.background;


import static com.bigoat.android.view.utils.ViewUtils.dp2px;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;


import com.bigoat.android.view.R;

import org.jetbrains.annotations.NotNull;


public class BackgroundView extends CardView {
    private int color;
    private boolean round;
    private int corner;
    private int cornerTopLeft;
    private int cornerTopRight;
    private int cornerBottomRight;
    private int cornerBottomLeft;

    public BackgroundView(Context context) {
        super(context);
        init(context, null);
    }

    public BackgroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setElevation(0);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BackgroundView);
        try {
            initAttrs(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    private void initAttrs(TypedArray typedArray) {
        color = typedArray.getColor(R.styleable.BackgroundView_backgroundColor, Color.TRANSPARENT);
        round = typedArray.getBoolean(R.styleable.BackgroundView_backgroundRound, false);
        corner = typedArray.getInt(R.styleable.BackgroundView_backgroundCorner, -1);
        cornerTopLeft = typedArray.getInt(R.styleable.BackgroundView_backgroundCornerTopLeft, -1);
        cornerTopRight = typedArray.getInt(R.styleable.BackgroundView_backgroundCornerTopRight, -1);
        cornerBottomRight = typedArray.getInt(R.styleable.BackgroundView_backgroundCornerBottomRight, -1);
        cornerBottomLeft = typedArray.getInt(R.styleable.BackgroundView_backgroundCornerBottomLeft, -1);

//        load(this, color, round, corner, cornerTopLeft, cornerTopRight, cornerBottomRight, cornerBottomLeft);
    }

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {
            "backgroundColor", // 源图片
            "background2Round", // 圆形图片
            "backgroundCorner", // 圆角大小
            "backgroundCornerTopLeft", // 圆角左上角大小
            "backgroundCornerTopRight", // 圆角右上角大小
            "backgroundCornerBottomRight", // 圆角左下角大小
            "backgroundCornerBottomLeft" // 圆角右下角大小
    }, requireAll = false)
    public static void load(@NotNull TextView view,
                            int color,
                            boolean round,
                            int corner,
                            int cornerTopLeft,
                            int cornerTopRight,
                            int cornerBottomRight,
                            int cornerBottomLeft) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setShape(round?GradientDrawable.OVAL:GradientDrawable.RECTANGLE);

        if (cornerTopLeft!=0 || cornerTopRight!=0 || cornerBottomLeft!=0 || cornerBottomRight!=0) {
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

    public static void load(@NotNull TextView view, String color, boolean round) {
        load(view, Color.parseColor(color), round, 0, 0, 0, 0, 0);
    }

    public static void load(@NotNull TextView view, String color, int corner) {
        load(view, Color.parseColor(color), false, corner, 0, 0, 0, 0);
    }
}
