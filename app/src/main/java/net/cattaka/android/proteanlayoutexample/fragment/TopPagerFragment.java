package net.cattaka.android.proteanlayoutexample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.cattaka.android.proteanlayoutexample.R;
import net.cattaka.android.proteanlayoutexample.adapter.factory.TopPagerAdapter;
import net.cattaka.android.proteanlayoutexample.databinding.FragmentTopPagerBinding;

/**
 * Created by cattaka on 17/02/12.
 */

public class TopPagerFragment extends Fragment {
    FragmentTopPagerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = FragmentTopPagerBinding.bind(view);

        TopPagerAdapter adapter = new TopPagerAdapter(getChildFragmentManager());
        mBinding.viewPager.setAdapter(adapter);
    }
}
