package net.cattaka.android.proteanlayoutexample.steps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.cattaka.android.proteanlayoutexample.R;
import net.cattaka.android.proteanlayoutexample.fragment.TopPagerFragment;

public class OldStyleTopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getSupportFragmentManager().findFragmentById(R.id.frame_primary) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_primary, new OldStyleTopPagerFragment(), null)
                    .commit();
        }
    }
}
