package com.bigoat.android.view.sidebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigoat.android.view.R;
import com.bigoat.android.view.utils.ViewUtils;


public class SidebarView extends LinearLayout {
    private RecyclerView recyclerView;
    private SidebarAdapter<?> adapter;
    private int selectColor;

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
    public String toString() {
        return super.toString();

    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();


    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(recyclerView);

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

        selectColor = ViewUtils.getTypedArrayColor(typedArray, R.styleable.SidebarView_sidebarSelectColor, Color.TRANSPARENT);
    }

    public void setAdapter(SidebarAdapter<?> adapter) {
        this.adapter = adapter;
        this.adapter.setSelectedColor(selectColor);
        recyclerView.setAdapter(adapter);
    }
}
