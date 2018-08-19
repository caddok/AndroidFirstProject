package com.example.georgi.allaboutthemoney.Dreams.create;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
import com.example.georgi.allaboutthemoney.R;
//TODO: Ask about urls and how to extract information;
public class CreateDreamActivity extends BaseDrawerActivity {
    public static final long IDENTIFIER = 210;
    private Toolbar mToolbar;
    private TextView test;
    private CreateDreamFragment mCreateDreamFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dream);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mCreateDreamFragment = CreateDreamFragment.getInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.create_fragment,mCreateDreamFragment)
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
