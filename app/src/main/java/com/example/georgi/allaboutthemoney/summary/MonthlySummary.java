package com.example.georgi.allaboutthemoney.summary;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
//TODO:remake as monthly summary of expenses and what is left for the month;include money per day
public class MonthlySummary extends BaseDrawerActivity {
    public static final long IDENTIFIER = 786;
    private Toolbar mToolbar;
    private MonthlySummaryFragment mMonthlySummaryFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.drawer_toolbar);
        setSupportActionBar(mToolbar);
        mMonthlySummaryFragment = MonthlySummaryFragment.getInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.monthly_summary,mMonthlySummaryFragment)
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
