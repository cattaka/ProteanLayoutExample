package net.cattaka.android.proteanlayoutexample.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by takao on 2017/03/16.
 */

public class PreloadLinearLayoutManager extends LinearLayoutManager {
    private int mDisplayHeight;

    public PreloadLinearLayoutManager(Context context) {
        super(context);
        initilize(context);
    }

    public PreloadLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        initilize(context);
    }

    public PreloadLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initilize(context);
    }

    public void initilize(Context context) {
        mDisplayHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        return mDisplayHeight;
    }
}
