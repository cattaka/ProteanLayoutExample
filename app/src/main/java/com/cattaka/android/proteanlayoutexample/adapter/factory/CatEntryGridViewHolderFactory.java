package com.cattaka.android.proteanlayoutexample.adapter.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cattaka.android.proteanlayoutexample.data.CatEntry;
import com.cattaka.android.proteanlayoutexample.databinding.ItemCatEntryGridBinding;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.adaptertoolbox.adapter.listener.ForwardingListener;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatEntryGridViewHolderFactory extends ScrambleAdapter.AbsViewHolderFactory<CatEntryGridViewHolderFactory.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ScrambleAdapter<?> adapter, @NonNull ViewGroup parent, @NonNull ForwardingListener<ScrambleAdapter<?>, RecyclerView.ViewHolder> forwardingListener) {
        return new ViewHolder(ItemCatEntryGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCatEntryGridBinding mBinding;

        public ViewHolder(ItemCatEntryGridBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
