package com.bigoat.android.view.binding;

import static com.bigoat.android.view.binding.BindingUtils.dp2px;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;

public class TextViewBinding {

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {
            "drawableStart",
            "drawableTop",
            "drawableEnd",
            "drawableBottom",
            "drawableSize",
            "drawablePadding",
            "drawableCorner",
            "drawableRound"
    }, requireAll = false)
    public static void textViewDrawable(TextView view, String start, String top, String end, String bottom, int size, int padding, int corner, boolean round) {
        if (padding > 0) {
            view.setCompoundDrawablePadding(dp2px(padding));
        }

        Drawable[] drawables = view.getCompoundDrawables();

        loadDrawable(view, start, drawables, 0, size, corner, round);
        loadDrawable(view, top, drawables, 1, size, corner, round);
        loadDrawable(view, end, drawables, 2, size, corner, round);
        loadDrawable(view, bottom, drawables, 3, size, corner, round);
    }

    @SuppressLint("CheckResult")
    private static void loadDrawable(TextView view, String url, Drawable[] drawables, int index, int size, int corner, boolean round) {
        if (drawables == null) {
            return;
        }

        RequestOptions requestOptions = new RequestOptions();

        if (size > 0) {
            requestOptions.override(size, dp2px(size));
        }

        if (corner > 0) {
            requestOptions.transform(new RoundedCorners(dp2px(corner)));
        }

        if (round) {
            requestOptions.circleCrop();
        }

        Glide.with(view)
                .load(url)
                .apply(requestOptions)
                .into(new CustomTarget<Drawable>() {

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                        drawables[index] = resource;
                        view.setCompoundDrawablesRelativeWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }
}

