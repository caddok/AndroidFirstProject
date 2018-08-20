package com.example.georgi.allaboutthemoney.Reports.monthly;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
import com.example.georgi.allaboutthemoney.R;


public class MonthlyExpensesReportActivity extends BaseDrawerActivity implements AdapterView.OnItemSelectedListener {
    public static final long IDENTIFIER = 580;
    private Toolbar mToolbar;
    private Spinner mMonthSpinner;
    private ArrayAdapter<CharSequence> mMonthsAdapter;
    private TextView mChosenDate;
    private MonthlyReportFragment mMonthlyReportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_expenses_report);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mMonthSpinner = findViewById(R.id.spinner_monthly_report);
        mChosenDate = findViewById(R.id.date);
        mMonthsAdapter = ArrayAdapter.createFromResource(this
                ,R.array.months,
                android.R.layout.simple_spinner_item);
        mMonthSpinner.setAdapter(mMonthsAdapter);
        mMonthlyReportFragment = MonthlyReportFragment.getInstance();
        mMonthSpinner.setOnItemSelectedListener(this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.monthly_report,mMonthlyReportFragment)
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0) {
            mMonthlyReportFragment.getMonth(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
