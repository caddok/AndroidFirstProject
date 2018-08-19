package com.example.georgi.allaboutthemoney.Dreams.current;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
import com.example.georgi.allaboutthemoney.R;
//TODO:Make fragment and put listing logic there
public class CurrentDreamActivity extends BaseDrawerActivity {
    public static final long IDENTIFIER = 359;
    private Toolbar mToolbar;
    private CurrentDreamFragment mCurrentDreamFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_dream);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mCurrentDreamFragment = CurrentDreamFragment.getInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.current_dreams,mCurrentDreamFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }
}
