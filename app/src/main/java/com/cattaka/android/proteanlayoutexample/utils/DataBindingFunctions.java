package com.cattaka.android.proteanlayoutexample.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by cattaka on 17/02/12.
 */

public class DataBindingFunctions {
    @BindingAdapter("loadImageFit")
    public static void loadImage(ImageView view, String url) {
        Picasso picasso = Picasso.with(view.getContext());
        if (url == null || url.length() == 0) {
            view.setImageDrawable(null);
            picasso.cancelRequest(view);
        } else {
            picasso.load(url).fit().into(view);
        }
    }
}
