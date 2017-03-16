package net.cattaka.android.proteanlayoutexample.utils;

import android.animation.ArgbEvaluator;
import android.databinding.BindingAdapter;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;

/**
 * Created by cattaka on 17/02/12.
 */

public class DataBindingFunctions {
    private static ArgbEvaluator mArgbEvaluator;

    @BindingAdapter(value = {"loadImage", "useFit", "loadImageTransformation", "loadImageListener"}, requireAll = false)
    public static void loadImage(final ImageView view, String url, boolean useFit, Transformation transformation, final ILoadImageListener listener) {
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
            if (transformation != null) {
                requestCreator = requestCreator.transform(transformation);
            }
            requestCreator.into(view, callback);
        }
    }

    @BindingAdapter("setLayoutWeight")
    public static void setLayoutWeight(View view, float value) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof LinearLayout.LayoutParams) {
            ((LinearLayout.LayoutParams) params).weight = value;
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("setLayoutMargin")
    public static void setLayoutMargin(View view, int value) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params).setMargins(value, value, value, value);
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("setLayoutMarginTop")
    public static void setLayoutMarginTop(View view, int value) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params).topMargin = value;
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("setLayoutMarginStart")
    public static void setLayoutMarginStart(View view, int value) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params).setMarginStart(value);
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("setLayoutMarginEnd")
    public static void setLayoutMarginEnd(View view, int value) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params).setMarginEnd(value);
            view.setLayoutParams(params);
        }
    }

    @BindingAdapter("setLayoutMarginBottom")
    public static void setLayoutMarginBottom(View view, int value) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) params).bottomMargin = value;
            view.setLayoutParams(params);
        }
    }

    public static float evaluateFactor(float position, float basePosition) {
        float factor = 1f - Math.abs(basePosition - position);
        if (factor < 0) {
            factor = 0;
        } else if (factor > 1) {
            factor = 1;
        }
        return factor;
    }

    public static float evaluateFactor(float position, float basePosition, float minValue, float maxValue) {
        float factor = 1f - Math.abs(basePosition - position);
        if (factor < 0) {
            factor = 0;
        } else if (factor > 1) {
            factor = 1;
        }
        return ((1f - factor) * minValue + factor * maxValue);
    }

    public static int evaluateColor(float position, float basePosition, @ColorInt int startValue, @ColorInt int endValue) {
        if (mArgbEvaluator == null) {
            mArgbEvaluator = new ArgbEvaluator();
        }
        float fraction = evaluateFactor(position, basePosition);
        return (Integer) mArgbEvaluator.evaluate(fraction, startValue, endValue);
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
