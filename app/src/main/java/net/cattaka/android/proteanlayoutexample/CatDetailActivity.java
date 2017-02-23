package net.cattaka.android.proteanlayoutexample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.proteanlayoutexample.adapter.factory.CatEntryGridViewHolderFactory;
import net.cattaka.android.proteanlayoutexample.data.CatEntry;
import net.cattaka.android.proteanlayoutexample.databinding.ActivityCatDetailBinding;
import net.cattaka.android.proteanlayoutexample.repo.Repository;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatDetailActivity extends AppCompatActivity {
    public static Intent createIntent(@NonNull Context context, @NonNull CatEntry item) {
        Intent intent = new Intent(context, CatDetailActivity.class);
        intent.putExtra("item", item);
        return intent;
    }


    ActivityCatDetailBinding mBinding;
    Repository mRepository;

    CatEntry mItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = new Repository(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cat_detail);
        mItem = (CatEntry) getIntent().getSerializableExtra("item");
        mBinding.setActivity(this);
        mBinding.setItem(mItem);

        mBinding.layoutContents.recyclerColor.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        mBinding.layoutContents.recyclerColor.setAdapter(new ScrambleAdapter<>(this, mRepository.findByColor(mItem.getColor()), null, new CatEntryGridViewHolderFactory()));
        mBinding.layoutContents.recyclerHair.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        mBinding.layoutContents.recyclerHair.setAdapter(new ScrambleAdapter<>(this, mRepository.findByHair(mItem.getHair()), null, new CatEntryGridViewHolderFactory()));
    }
}
