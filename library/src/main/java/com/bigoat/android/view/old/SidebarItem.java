package com.bigoat.android.view.old;

import android.graphics.drawable.Drawable;

import com.bigoat.android.view.R;


public class SidebarItem {
    public String name;

    public int[] color = {R.color.v_info, R.color.v_content_color};
    public int[] size = {16, 14};

    public Drawable[] leftIcon;
    public Drawable[] rightIcon;
    public Drawable[] topIcon;
    public Drawable[] bottomIcon;
    public boolean selected;

    public SidebarItem(String name) {
        this.name = name;
    }
}
