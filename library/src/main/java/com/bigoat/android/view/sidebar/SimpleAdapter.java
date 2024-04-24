package com.bigoat.android.view.sidebar;

import android.widget.TextView;

import androidx.annotation.NonNull;


import com.bigoat.android.view.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends SidebarAdapter<SidebarItem>{

    public SimpleAdapter(List<SidebarItem> data) {
        super(R.layout.sidebar_item, data);
    }

    public SimpleAdapter(String ...data) {
        super(R.layout.sidebar_item);
        setData(data);
    }

    public void setData(String ...data) {
        List<SidebarItem> items = new ArrayList<>();
        for (String string : data) {
            items.add(new SidebarItem(string));
        }
       setData(items);
    }

    @Override
    public void convert(@NonNull ViewHolder holder, SidebarItem item, int position, boolean selected) {
        TextView textView = holder.itemView.findViewById(R.id.textView);
        textView.setText(item.name);
        if (selected) {
            textView.setTextColor(item.color[0]);
            if (item.size!=null && item.size.length>0) textView.setTextSize(item.size[0]);
            if (item.leftIcon!=null && item.leftIcon.length>0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(item.leftIcon[0],null, null,null);
            }
            if (item.topIcon!=null && item.topIcon.length>0) {
                item.topIcon[0].setBounds(0, 0, 10, 10);
                textView.setCompoundDrawablesWithIntrinsicBounds(null,item.topIcon[0], null,null);
            }
            if (item.rightIcon!=null && item.rightIcon.length>0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null,null, item.rightIcon[0],null);
            }
            if (item.bottomIcon!=null && item.bottomIcon.length>0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null,null, null,item.bottomIcon[0]);
            }
        } else {
            textView.setTextColor(item.color[1]);
            if (item.size!=null && item.size.length>1) textView.setTextSize(item.size[1]);
            if (item.leftIcon!=null && item.leftIcon.length>1) {
                textView.setCompoundDrawablesWithIntrinsicBounds(item.leftIcon[1],null, null,null);
            }
            if (item.topIcon!=null && item.topIcon.length>1) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null,item.topIcon[1], null,null);
            }
            if (item.rightIcon!=null && item.rightIcon.length>1) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null,null, item.rightIcon[1],null);
            }
            if (item.bottomIcon!=null && item.bottomIcon.length>1) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null,null, null,item.bottomIcon[1]);
            }

        }

    }

}
