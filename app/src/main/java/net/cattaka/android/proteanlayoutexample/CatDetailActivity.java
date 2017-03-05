package net.cattaka.android.proteanlayoutexample;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Window;
import android.view.WindowManager;
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
                Palette.from(bitmap).generate(mPaletteAsyncListener);
            }
        }

        @Override
        public void onError(@NonNull ImageView imageView) {
            // ignore
        }
    };

    Palette.PaletteAsyncListener mPaletteAsyncListener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            mVibrantColor = palette.getVibrantColor(0xFF000000 | getResources().getColor(R.color.statusBarDetail));
            mBinding.setVibrantColor(mVibrantColor);
        }
    };

    NestedScrollView.OnScrollChangeListener mOnScrollChangeListener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            float factor = (float) scrollY / (float) mBinding.layoutContents.image.getHeight();
            if (factor < 0f) {
                factor = 0f;
            } else if (factor > 1f) {
                factor = 1f;
            }
            int color = DataBindingFunctions.evaluateColor(factor, mOriginalStatusBarColor, mVibrantColor);
            setStatusBarColor(color);

            mBinding.setScrollFactor(factor);
        }
    };

    ActivityCatDetailBinding mBinding;
    Repository mRepository;

    int mOriginalStatusBarColor;
    int mVibrantColor;
    CatEntry mItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        mOriginalStatusBarColor = getResources().getColor(R.color.statusBarDetail);
        setStatusBarColor(mOriginalStatusBarColor);

        mRepository = Repository.getInstance(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cat_detail);
        mItem = (CatEntry) getIntent().getSerializableExtra("item");
        mBinding.setActivity(this);
        mBinding.setItem(mItem);
        mBinding.setOriginalStatusBarColor(mOriginalStatusBarColor);

        mBinding.layoutContents.scroll.setOnScrollChangeListener(mOnScrollChangeListener);

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

    private void setStatusBarColor(int color) {
        Window window = getWindow();
        if (window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }
    }

    public DataBindingFunctions.ILoadImageListener getLoadImageListener() {
        return mLoadImageListener;
    }
}
