package com.bigoat.android.view.sidebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;

import com.bigoat.android.view.R;
import com.bigoat.android.view.utils.CopyViewUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;

public class SidebarItemView extends LinearLayout {
    private TextView textView;
    private ImageView iconView;

    public SidebarItemView(Context context) {
        super(context);
        init(context, null);
    }

    public SidebarItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SidebarItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SidebarView);

        try {
            initAttrs(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    @SuppressLint("ResourceAsColor")
    private void initAttrs(TypedArray typedArray) {

        int layout = typedArray.getResourceId(R.styleable.SidebarView_sidebarData, -1);
    }


    private void updateView() {

    }

}
