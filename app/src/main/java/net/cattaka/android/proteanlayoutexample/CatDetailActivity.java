package net.cattaka.android.proteanlayoutexample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.cattaka.android.proteanlayoutexample.data.CatEntry;
import net.cattaka.android.proteanlayoutexample.databinding.ActivityCatDetailBinding;

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

    CatEntry mItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cat_detail);
        mItem = (CatEntry) getIntent().getSerializableExtra("item");
        mBinding.setActivity(this);
        mBinding.setItem(mItem);
    }
}
