package com.lwy.dbindingview.bindingadapter.image;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public final class ViewBindingAdapter {

    @BindingAdapter({"uri"})
    public static void setImageUri(ImageView imageView, String uri) {
        if (!TextUtils.isEmpty(uri)) {
            Glide.with(imageView.getContext()).load(uri).into(imageView);
        }
    }


    @BindingAdapter(value = {"uri", "placeholderImageRes", "request_width", "request_height"}, requireAll = false)
    public static void loadImage(ImageView imageView, String uri,
                                 @DrawableRes int placeholderImageRes,
                                 int width, int height) {
        Glide.with(imageView.getContext())
                .load(uri).into(imageView);
//                .placeholder(placeholderImageRes);
//        if (width > 0 && height > 0) {
//            builder.override(width, height);
//        }
//        builder.into(imageView);
    }

}

