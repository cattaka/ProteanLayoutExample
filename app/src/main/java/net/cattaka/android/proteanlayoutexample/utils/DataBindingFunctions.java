package net.cattaka.android.proteanlayoutexample.utils;

import android.animation.ArgbEvaluator;
import android.databinding.BindingAdapter;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by cattaka on 17/02/12.
 */

public class DataBindingFunctions {
    private static ArgbEvaluator mArgbEvaluator;

    @BindingAdapter(value = {"loadImage", "useFit", "loadImageListener"}, requireAll = false)
    public static void loadImage(final ImageView view, String url, boolean useFit, final ILoadImageListener listener) {
        Picasso picasso = Picasso.with(view.getContext());
        if (url == null || url.length() == 0) {
            view.setImageDrawable(null);
            picasso.cancelRequest(view);
        } else {
            Callback callback = null;
            if (listener != null) {
                callback = new Callback() {
                    @Override
                    public void onSuccess() {
                        listener.onSuccess(view);
                    }

                    @Override
                    public void onError() {
                        listener.onError(view);
                    }
                };
            }
            RequestCreator requestCreator = picasso.load(url);
            if (useFit) {
                requestCreator = requestCreator.fit();
            }
            requestCreator.into(view, callback);
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

    public interface ILoadImageListener {
        void onSuccess(@NonNull ImageView imageView);

        void onError(@NonNull ImageView imageView);
    }
}
