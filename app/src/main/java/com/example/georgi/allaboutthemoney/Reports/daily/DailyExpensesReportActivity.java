package com.example.georgi.allaboutthemoney.Reports.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.CalendarActivity;

import java.util.Calendar;
import java.util.Date;

public class DailyExpensesReportActivity extends BaseDrawerActivity implements View.OnClickListener {
    public static final long IDENTIFIER = 26;
    private Toolbar mToolbar;
    private Button mSelectDate;
    private TextView theDate;
    private DailyReportFragment mDailyReportFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses_report);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mSelectDate = findViewById(R.id.btn_go_to_calendar);
        mSelectDate.setOnClickListener(this);
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate = findViewById(R.id.chosenDate);
        theDate.setText(date);
        mDailyReportFragment = DailyReportFragment.getInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.daily_expenses,mDailyReportFragment)
                .commit();
        mDailyReportFragment.getDate(date);
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
        intent.putExtra("identifier",String.valueOf(DailyExpensesReportActivity.IDENTIFIER));
        startActivity(intent);
    }
}
