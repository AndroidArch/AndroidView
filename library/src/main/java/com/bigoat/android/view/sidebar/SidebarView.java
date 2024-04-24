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

public class SidebarView extends ScrollView {

    public SidebarView(Context context) {
        super(context);
        init(context, null);
    }

    public SidebarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SidebarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        View view = getChildAt(0);
        removeView(view);

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 20; i++) {
            TextView textView = CopyViewUtils.copyTextView(getContext(), (TextView) view);
            textView.setText("Item " + i);
            layout.addView(textView);
        }

        addView(layout);
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
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < 20; i++) {
            TextView textView = new TextView(getContext());
            textView.setText("Item " + i);
            layout.addView(textView);
        }

        addView(layout);
    }

    public final class TabView extends LinearLayout {
        private TabLayout.Tab tab;
        private TextView textView;
        private ImageView iconView;
        @Nullable
        private View badgeAnchorView;
        @Nullable
        private BadgeDrawable badgeDrawable;
        @Nullable
        private View customView;
        @Nullable
        private TextView customTextView;
        @Nullable
        private ImageView customIconView;
        @Nullable
        private Drawable baseBackgroundDrawable;
        private int defaultMaxLines = 2;

        public TabView(@NonNull Context context) {
            super(context);
            this.setGravity(17);
            this.setClickable(true);
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
        }
    }
    public static class Tab {
        public static final int INVALID_POSITION = -1;
        @Nullable
        private Object tag;
        @Nullable
        private Drawable icon;
        @Nullable
        private CharSequence text;
        @Nullable
        private CharSequence contentDesc;
        private int position = -1;
        @Nullable
        private View customView;
        @Nullable
        public TabLayout parent;
        @NonNull
        public TabLayout.TabView view;
        private int id = -1;

        public Tab() {
        }
    }

}
