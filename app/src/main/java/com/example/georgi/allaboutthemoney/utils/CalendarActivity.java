package com.example.georgi.allaboutthemoney.utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

import com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.daily.DailyExpensesActivity;
import com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.daily.DailyExpensesFragment;
import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.Reports.daily.DailyReportFragment;
import com.example.georgi.allaboutthemoney.Reports.monthly.MonthlyExpensesReportActivity;
import com.example.georgi.allaboutthemoney.Reports.weekly.WeeklyExpensesReportActivity;
import com.example.georgi.allaboutthemoney.Reports.daily.DailyExpensesReportActivity;

public class CalendarActivity extends AppCompatActivity {
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = findViewById(R.id.calendar_view);
        Intent incoming = getIntent();
        String identifier = incoming.getStringExtra("identifier");
        mCalendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            int monthAsInt = Integer.parseInt(String.valueOf(month)) + 1;
            month = Integer.parseInt(String.valueOf(monthAsInt));
            String date  = dayOfMonth + "-" + month + "-" + year;
            Intent intent;
            if (identifier.equals(String.valueOf(DailyExpensesReportActivity.IDENTIFIER))) {
                intent = new Intent(CalendarActivity.this,DailyExpensesReportActivity.class);
            } else if (identifier.equals(String.valueOf(WeeklyExpensesReportActivity.IDENTIFIER))) {
                intent = new Intent(CalendarActivity.this,WeeklyExpensesReportActivity.class);
            } else if (identifier.equals(String.valueOf(DailyExpensesActivity.IDENTIFIER))) {
                intent = new Intent(CalendarActivity.this,DailyExpensesActivity.class);
            }
            else {
                intent = new Intent(CalendarActivity.this, MonthlyExpensesReportActivity.class);
            }
            intent.putExtra("date",date);
            startActivity(intent);
        });
    }
}
