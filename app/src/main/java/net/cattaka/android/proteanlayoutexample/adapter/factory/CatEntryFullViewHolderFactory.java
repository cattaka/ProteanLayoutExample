package net.cattaka.android.proteanlayoutexample.adapter.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.adaptertoolbox.adapter.listener.ForwardingListener;
import net.cattaka.android.proteanlayoutexample.adapter.ScrollEventRelay;
import net.cattaka.android.proteanlayoutexample.data.CatEntry;
import net.cattaka.android.proteanlayoutexample.databinding.ItemCatEntryFullBinding;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatEntryFullViewHolderFactory extends ScrambleAdapter.AbsViewHolderFactory<CatEntryFullViewHolderFactory.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ScrambleAdapter<?> adapter, @NonNull ViewGroup parent, @NonNull ForwardingListener<ScrambleAdapter<?>, RecyclerView.ViewHolder> forwardingListener) {
        ViewHolder holder = new ViewHolder(ItemCatEntryFullBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        holder.itemView.setOnClickListener(forwardingListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScrambleAdapter<?> adapter, @NonNull ViewHolder holder, int position, @Nullable Object object) {
        super.onBindViewHolder(adapter, holder, position, object);
        holder.mBinding.setItem((CatEntry) object);
    }

    @Override
    public boolean isAssignable(Object object) {
        return object instanceof CatEntry;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ScrollEventRelay.IScrollEventRelayListener {
        ItemCatEntryFullBinding mBinding;

        public ViewHolder(ItemCatEntryFullBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, float factor) {
            float sizeFactor = Math.max(0f, Math.min(factor * 4, 1f));
            float offsetFactor = Math.max(0f, Math.min(factor, 1f));
            if (offsetFactor > 0.9f) {
                offsetFactor = (1f - offsetFactor) * 10f;
            }

            mBinding.setSizeFactor(sizeFactor);
            mBinding.setOffsetFactor(offsetFactor);
            mBinding.setScrollFactor(factor);
        }
    }
}
