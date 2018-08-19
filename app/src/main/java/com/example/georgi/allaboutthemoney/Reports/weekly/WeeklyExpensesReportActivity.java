package com.example.georgi.allaboutthemoney.Reports.weekly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.CalendarActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeeklyExpensesReportActivity extends BaseDrawerActivity implements View.OnClickListener {
    public static final long IDENTIFIER = 73;
    private Toolbar mToolbar;
    private Button mChooseDate;
    private TextView mDate;
    private WeeklyReportFragment mWeeklyReportFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_expenses_report);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mChooseDate = findViewById(R.id.btn_weekly_report);
        mDate = findViewById(R.id.tv_date);
        mChooseDate.setOnClickListener(this);

        Intent incoming = getIntent();
        String chosenDate = incoming.getStringExtra("date");
        mDate.setText(chosenDate);
        mWeeklyReportFragment = WeeklyReportFragment.getInstance();
        mWeeklyReportFragment.getDate(chosenDate);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.weekly_report,mWeeklyReportFragment)
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
    public void onClick(View v) {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("identifier",String.valueOf(WeeklyExpensesReportActivity.IDENTIFIER));
        startActivity(intent);
    }


}
