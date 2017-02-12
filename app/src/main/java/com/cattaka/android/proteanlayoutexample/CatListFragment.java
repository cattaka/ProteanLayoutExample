package com.cattaka.android.proteanlayoutexample;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cattaka.android.proteanlayoutexample.adapter.factory.CatEntryViewHolderFactory;
import com.cattaka.android.proteanlayoutexample.data.CatEntries;
import com.cattaka.android.proteanlayoutexample.data.CatEntry;
import com.cattaka.android.proteanlayoutexample.databinding.FragmentCatListBinding;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.adaptertoolbox.adapter.listener.ListenerRelay;

import java.util.ArrayList;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatListFragment extends Fragment {
    ListenerRelay<ScrambleAdapter<?>, RecyclerView.ViewHolder> mListenerRelay = new ListenerRelay<ScrambleAdapter<?>, RecyclerView.ViewHolder>() {
        @Override
        public void onClick(@NonNull RecyclerView recyclerView, @NonNull ScrambleAdapter<?> adapter, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull View view) {
            super.onClick(recyclerView, adapter, viewHolder, view);
        }
    };

    FragmentCatListBinding mBinding;
    ScrambleAdapter<CatEntry> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = FragmentCatListBinding.bind(view);

        mAdapter = new ScrambleAdapter<CatEntry>(getContext(), new ArrayList<CatEntry>(), mListenerRelay, new CatEntryViewHolderFactory());
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.recycler.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    private void loadData() {
        try {
            AssetManager assetManager = getContext().getAssets();
            ObjectMapper objectMapper = new ObjectMapper();
            CatEntries catEntries = objectMapper.readValue(assetManager.open("data/data.json"), CatEntries.class);
            mAdapter.getItems().clear();
            mAdapter.getItems().addAll(catEntries.getItems());
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
