package net.cattaka.android.proteanlayoutexample.adapter.factory;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.adaptertoolbox.adapter.listener.ForwardingListener;
import net.cattaka.android.proteanlayoutexample.data.CatEntry;
import net.cattaka.android.proteanlayoutexample.databinding.ItemCatEntrySmallCardBinding;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatEntrySmallCardViewHolderFactory extends ScrambleAdapter.AbsViewHolderFactory<CatEntrySmallCardViewHolderFactory.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ScrambleAdapter<?> adapter, @NonNull ViewGroup parent, @NonNull ForwardingListener<ScrambleAdapter<?>, RecyclerView.ViewHolder> forwardingListener) {
        ViewHolder holder = new ViewHolder(ItemCatEntrySmallCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCatEntrySmallCardBinding mBinding;

        public ViewHolder(ItemCatEntrySmallCardBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
