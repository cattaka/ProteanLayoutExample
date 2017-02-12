package net.cattaka.android.proteanlayoutexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.cattaka.android.proteanlayoutexample.R;

import net.cattaka.android.proteanlayoutexample.fragment.TopPagerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getSupportFragmentManager().findFragmentById(R.id.frame_primary) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_primary, new TopPagerFragment(), null)
                    .commit();
        }
    }
}
