package net.cattaka.android.proteanlayoutexample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.adaptertoolbox.adapter.listener.ListenerRelay;
import net.cattaka.android.proteanlayoutexample.R;
import net.cattaka.android.proteanlayoutexample.adapter.factory.CatEntryGridViewHolderFactory;
import net.cattaka.android.proteanlayoutexample.core.IShowDetail;
import net.cattaka.android.proteanlayoutexample.data.CatEntry;
import net.cattaka.android.proteanlayoutexample.databinding.FragmentCatGridBinding;
import net.cattaka.android.proteanlayoutexample.repo.Repository;

import java.util.ArrayList;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatGridFragment extends Fragment {
    ListenerRelay<ScrambleAdapter<?>, RecyclerView.ViewHolder> mListenerRelay = new ListenerRelay<ScrambleAdapter<?>, RecyclerView.ViewHolder>() {
        @Override
        public void onClick(@NonNull RecyclerView recyclerView, @NonNull ScrambleAdapter<?> adapter, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull View view) {
            super.onClick(recyclerView, adapter, viewHolder, view);
            if (viewHolder instanceof CatEntryGridViewHolderFactory.ViewHolder) {
                CatEntry item = (CatEntry) mAdapter.getItemAt(viewHolder.getAdapterPosition());
                showDetail(item);
            }
        }
    };

    FragmentCatGridBinding mBinding;
    ScrambleAdapter<CatEntry> mAdapter;
    Repository mRepository;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRepository = Repository.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_grid, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = FragmentCatGridBinding.bind(view);

        mAdapter = new ScrambleAdapter<CatEntry>(getContext(), new ArrayList<CatEntry>(), mListenerRelay, new CatEntryGridViewHolderFactory());
        mBinding.recycler.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        mBinding.recycler.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData() {
        try {
            mAdapter.getItems().clear();
            mAdapter.getItems().addAll(mRepository.getCatEntries());
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showDetail(@NonNull CatEntry item) {
        ((IShowDetail)getActivity()).showDetail(item);
    }
}
