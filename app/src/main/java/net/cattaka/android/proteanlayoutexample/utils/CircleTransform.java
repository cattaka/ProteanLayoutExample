package net.cattaka.android.proteanlayoutexample.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by cattaka on 17/02/27.
 */
public class CircleTransform implements Transformation {
    private static CircleTransform INSTANCE = new CircleTransform();

    public static CircleTransform getInstance() {
        return INSTANCE;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        return transform(source, true);
    }

    public static Bitmap transform(Bitmap source, boolean recycle) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            if (recycle) {
                source.recycle();
            }
        }

        Bitmap bitmap;
        if (source.getConfig() != null) {
            bitmap = Bitmap.createBitmap(size, size, source.getConfig());
        } else {
            // If the internal format for the image is not in the public list in Bitmap.java, then null is returned.
            // https://code.google.com/p/android/issues/detail?id=2628
            bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CircleTransform;
    }
}
