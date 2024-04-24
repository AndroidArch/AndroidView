package com.bigoat.android.view.sidebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigoat.android.view.R;
import com.bigoat.android.view.utils.CopyViewUtils;

public class SidebarView2 extends ScrollView {

    public SidebarView2(Context context) {
        super(context);
        init(context, null);
    }

    public SidebarView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SidebarView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
}
