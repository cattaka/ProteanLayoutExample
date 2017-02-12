package net.cattaka.android.proteanlayoutexample.adapter.factory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.cattaka.android.proteanlayoutexample.fragment.CatGridFragment;
import net.cattaka.android.proteanlayoutexample.fragment.CatListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cattaka on 17/02/12.
 */

public class TopPagerAdapter extends FragmentPagerAdapter {
    private List<Class<? extends Fragment>> mFragmentClasses;

    public TopPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentClasses = new ArrayList<>();
        mFragmentClasses.add(CatListFragment.class);
        mFragmentClasses.add(CatGridFragment.class);
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return mFragmentClasses.get(position).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);  // Can not handle
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);  // Can not handle
        }
    }

    @Override
    public int getCount() {
        return mFragmentClasses.size();
    }
}
