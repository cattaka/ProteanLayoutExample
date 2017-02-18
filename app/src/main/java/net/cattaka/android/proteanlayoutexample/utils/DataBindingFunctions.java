package net.cattaka.android.proteanlayoutexample.utils;

import android.animation.ArgbEvaluator;
import android.databinding.BindingAdapter;
import android.support.annotation.ColorInt;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by cattaka on 17/02/12.
 */

public class DataBindingFunctions {
    private static ArgbEvaluator mArgbEvaluator;

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

    public static int evaluateColor(float fraction, @ColorInt int startValue, @ColorInt int endValue) {
        if (mArgbEvaluator == null) {
            mArgbEvaluator = new ArgbEvaluator();
        }
        return (Integer) mArgbEvaluator.evaluate(fraction, startValue, endValue);
    }

    public static int evaluateColor(float fraction, @ColorInt int startValue, @ColorInt int centerValue, @ColorInt int endValue) {
        if (mArgbEvaluator == null) {
            mArgbEvaluator = new ArgbEvaluator();
        }
        if (fraction <= 0.5) {
            return (Integer) mArgbEvaluator.evaluate(fraction * 2, startValue, centerValue);
        } else {
            return (Integer) mArgbEvaluator.evaluate((fraction - 0.5f) * 2, centerValue, endValue);
        }
    }
}
