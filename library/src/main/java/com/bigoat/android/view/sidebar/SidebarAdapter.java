package com.bigoat.android.view.sidebar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public abstract class SidebarAdapter<T> extends RecyclerView.Adapter<SidebarAdapter.ViewHolder>{
    protected @LayoutRes int layoutId;
    protected List<T> data;
    protected ViewPager2 viewPager;

    protected int selectedPosition = -1;
    protected int selectedColor = 0x969799;

    public SidebarAdapter(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public SidebarAdapter(@LayoutRes int layoutId, List<T> data) {
        this.layoutId = layoutId;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.setBackgroundColor(this.selectedPosition==position?selectedColor:Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> {
           this.selectedPosition = position;
           notifyDataSetChanged();
            if (viewPager != null) {
                viewPager.setCurrentItem(position, true);
            }
        });

        convert(holder, data.get(position), position, selectedPosition==position);
    }


    public abstract void convert(@NonNull ViewHolder holder, T item, int position, boolean selected);

    @Override
    public int getItemCount() {
        if (data==null) return 0;
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void bindViewPager(ViewPager2 viewPager) {
        bindViewPager(viewPager, true, 0);
    }
    public void bindViewPager(ViewPager2 viewPager, boolean linkage, int position) {
        this.viewPager = viewPager;
        this.selectedPosition = position;

        if (linkage) {
            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
            });
        }

        if (position!=-1) {
            viewPager.setCurrentItem(position, true);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }

        public void setText(int textView, String text) {
            TextView tv = itemView.findViewById(textView);
            tv.setText(text);
        }
    }
}