package net.cattaka.android.proteanlayoutexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by takao on 2017/03/16.
 */

public class ScrollEventRelay extends RecyclerView.OnScrollListener {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View child = recyclerView.getChildAt(i);
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
            if (holder instanceof IScrollEventRelayListener) {
                float factor = (float) child.getTop() / (float) recyclerView.getHeight();
                ((IScrollEventRelayListener) holder).onScrolled(recyclerView, factor);
            }
        }
    }

    public interface IScrollEventRelayListener {
        void onScrolled(RecyclerView recyclerView, float factor);
    }
}
