package com.bigoat.android.view.binding;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.net.URL;

public class ImageBinding {

    @SuppressLint("CheckResult")
    @BindingAdapter(value = {
            "src",
            "holder",
            "error",
            "size",
            "width",
            "height",
            "round",
            "corner",
            "cornerTopLeft",
            "cornerTopRight",
            "cornerBottomRight",
            "cornerBottomLeft"}, requireAll = false)
    public static void load(
            ImageView view,
            Object src,
            Drawable holder,
            Drawable error,
            int imageSize,
            int imageWidth,
            int imageHeight,
            boolean round,
            int imageCorner,
            int imageCornerTopLeft,
            int imageCornerTopRight,
            int imageCornerBottomRight,
            int imageCornerBottomLeft) {
        RequestOptions options = new RequestOptions();
        if (holder != null) options.placeholder(holder);
        if (error != null) options.error(error);
        if (imageCornerBottomLeft > 0 || imageCornerBottomRight > 0 || imageCornerTopLeft > 0 || imageCornerTopRight > 0) {
            options.transform(new GranularRoundedCorners(imageCornerTopLeft, imageCornerTopRight, imageCornerBottomRight, imageCornerBottomLeft));
        }
        if (imageCorner > 0) {
            options.transform(new RoundedCorners(imageCorner));
        }
        if (round) {
            options.circleCrop();
        }

        if (imageSize > 0) {
            options.override(imageSize);
        } else if (imageWidth > 0 && imageHeight > 0) {
            options.override(imageWidth, imageHeight);
        }

        load(view, src, options);
    }

    public static void load(ImageView view, Object src) {
        load(view, src, null, null, 0, 0, 0, false, 0, 0, 0, 0, 0);
    }

    public static void load(ImageView view, Object src, Drawable error) {
        load(view, src, null, error, 0, 0, 0, false, 0, 0, 0, 0, 0);
    }

    public static void load(ImageView view, Object src, Drawable holder, Drawable error) {
        load(view, src, holder, error, 0, 0, 0, false, 0, 0, 0, 0, 0);
    }

    private static void load(ImageView view, Object src, RequestOptions options) {
        if (src instanceof Bitmap) {
            Glide.with(view).load((Bitmap) src).apply(options).into(view);
        } else if (src instanceof Drawable) {
            Glide.with(view).load((Drawable) src).apply(options).into(view);
        } else if (src instanceof String) {
            Glide.with(view).load((String) src).apply(options).into(view);
        } else if (src instanceof Uri) {
            Glide.with(view).load((Uri) src).apply(options).into(view);
        } else if (src instanceof File) {
            Glide.with(view).load((File) src).apply(options).into(view);
        } else if (src instanceof URL) {
            Glide.with(view).load((URL) src).apply(options).into(view);
        } else if (src instanceof Integer) {
            Glide.with(view).load((Integer) src).apply(options).into(view);
        } else {
            Glide.with(view).load(src).apply(options).into(view);
        }
    }
}
