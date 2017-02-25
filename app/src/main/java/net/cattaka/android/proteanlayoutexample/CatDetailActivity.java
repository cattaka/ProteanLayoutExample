package net.cattaka.android.proteanlayoutexample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.proteanlayoutexample.adapter.factory.CatEntrySmallCardViewHolderFactory;
import net.cattaka.android.proteanlayoutexample.data.CatEntry;
import net.cattaka.android.proteanlayoutexample.databinding.ActivityCatDetailBinding;
import net.cattaka.android.proteanlayoutexample.repo.Repository;
import net.cattaka.android.proteanlayoutexample.utils.DataBindingFunctions;

import java.util.List;

/**
 * Created by cattaka on 17/02/12.
 */

public class CatDetailActivity extends AppCompatActivity {
    public static Intent createIntent(@NonNull Context context, @NonNull CatEntry item) {
        Intent intent = new Intent(context, CatDetailActivity.class);
        intent.putExtra("item", item);
        return intent;
    }

    DataBindingFunctions.ILoadImageListener mLoadImageListener = new DataBindingFunctions.ILoadImageListener() {
        @Override
        public void onSuccess(@NonNull ImageView imageView) {
            Drawable drawable = imageView.getDrawable();
            BitmapDrawable bitmapDrawable = (drawable instanceof BitmapDrawable) ? (BitmapDrawable) drawable : null;
            Bitmap bitmap = (bitmapDrawable != null) ? bitmapDrawable.getBitmap() : null;
            if (bitmap != null) {
                // TODO
            }
        }

        @Override
        public void onError(@NonNull ImageView imageView) {
            // ignore
        }
    };

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

        {
            List<CatEntry> items = Repository.removeByName(mRepository.findByColor(mItem.getColor()), mItem.getName());
            ScrambleAdapter<CatEntry> adapter = new ScrambleAdapter<>(this, items, null, new CatEntrySmallCardViewHolderFactory());
            mBinding.layoutContents.recyclerColor.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
            mBinding.layoutContents.recyclerColor.setAdapter(adapter);
        }
        {
            List<CatEntry> items = Repository.removeByName(mRepository.findByHair(mItem.getHair()), mItem.getName());
            ScrambleAdapter<CatEntry> adapter = new ScrambleAdapter<>(this, items, null, new CatEntrySmallCardViewHolderFactory());
            mBinding.layoutContents.recyclerHair.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
            mBinding.layoutContents.recyclerHair.setAdapter(adapter);
        }
    }


    public DataBindingFunctions.ILoadImageListener getLoadImageListener() {
        return mLoadImageListener;
    }
}
