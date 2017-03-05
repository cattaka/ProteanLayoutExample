package net.cattaka.android.proteanlayoutexample;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import net.cattaka.android.adaptertoolbox.adapter.listener.ListenerRelay;
import net.cattaka.android.proteanlayoutexample.adapter.ActivityEntryAdapter;
import net.cattaka.android.proteanlayoutexample.data.ActivityEntry;
import net.cattaka.android.proteanlayoutexample.databinding.ActivityMainBinding;
import net.cattaka.android.proteanlayoutexample.steps.OldStyleTopActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final List<ActivityEntry> ACTIVITY_ENTRIES = Arrays.asList(
            new ActivityEntry("完成形", TopActivity.class),
            new ActivityEntry("OldStyle", OldStyleTopActivity.class)
    );

    ListenerRelay<ActivityEntryAdapter, ActivityEntryAdapter.ViewHolder> mListenerRelay = new ListenerRelay<ActivityEntryAdapter, ActivityEntryAdapter.ViewHolder>() {
        @Override
        public void onClick(RecyclerView recyclerView, ActivityEntryAdapter adapter, ActivityEntryAdapter.ViewHolder viewHolder, View view) {
            if (recyclerView.getId() == R.id.recycler) {
                ActivityEntry entry = adapter.getItemAt(viewHolder.getAdapterPosition()).getItem();
                if (entry != null && entry.getClazz() != null) {
                    if (entry.getApiLevel() > Build.VERSION.SDK_INT) {
                        Toast.makeText(MainActivity.this, "This is for over api level " + entry.getApiLevel(), Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, entry.getClazz());
                        startActivity(intent);
                    }
                }
            }
        }
    };

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ActivityEntryAdapter adapter = new ActivityEntryAdapter(this, ACTIVITY_ENTRIES);
        adapter.setListenerRelay(mListenerRelay);

        mBinding.recycler.setAdapter(adapter);
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
