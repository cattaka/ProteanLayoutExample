package net.cattaka.android.proteanlayoutexample.steps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import net.cattaka.android.proteanlayoutexample.R;
import net.cattaka.android.proteanlayoutexample.adapter.factory.TopPagerAdapter;
import net.cattaka.android.proteanlayoutexample.databinding.FragmentOldStyleTopPagerBinding;

/**
 * Created by cattaka on 17/02/12.
 */

public class OldStyleTopPagerFragment extends Fragment {
    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            mBinding.setPosition(position);

            int translationY = 0;
            if (position != 0) {
                translationY = mBinding.layout.getHeight() - mBinding.buttonSearch.getTop();
            }
            mBinding.buttonSearch.animate()
                    .translationY(translationY)
                    .start();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    FragmentOldStyleTopPagerBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_old_style_top_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = FragmentOldStyleTopPagerBinding.bind(view);
        mBinding.setFragment(this);

        TopPagerAdapter adapter = new TopPagerAdapter(getChildFragmentManager());
        mBinding.viewPager.setAdapter(adapter);
        mBinding.viewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

    public void onClickSearch(View view) {
        Toast.makeText(getContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
    }
}
