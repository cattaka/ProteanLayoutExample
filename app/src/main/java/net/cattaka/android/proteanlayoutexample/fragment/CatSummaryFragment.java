package net.cattaka.android.proteanlayoutexample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.proteanlayoutexample.R;
import net.cattaka.android.proteanlayoutexample.adapter.factory.AggregatedEntryViewHolderFactory;
import net.cattaka.android.proteanlayoutexample.data.AggregatedEntry;
import net.cattaka.android.proteanlayoutexample.databinding.FragmentCatSummaryBinding;
import net.cattaka.android.proteanlayoutexample.repo.Repository;

import java.util.ArrayList;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatSummaryFragment extends Fragment {
    FragmentCatSummaryBinding mBinding;
    ScrambleAdapter<AggregatedEntry> mColorAdapter;
    ScrambleAdapter<AggregatedEntry> mHairAdapter;
    Repository mRepository;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mRepository = Repository.getInstance(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cat_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = FragmentCatSummaryBinding.bind(view);

        mColorAdapter = new ScrambleAdapter<>(getContext(), new ArrayList<>(mRepository.aggregateByColor()), null, new AggregatedEntryViewHolderFactory());
        mBinding.recyclerColor.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerColor.setAdapter(mColorAdapter);

        mHairAdapter = new ScrambleAdapter<>(getContext(), new ArrayList<>(mRepository.aggregateByHair()), null, new AggregatedEntryViewHolderFactory());
        mBinding.recyclerHair.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerHair.setAdapter(mHairAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
