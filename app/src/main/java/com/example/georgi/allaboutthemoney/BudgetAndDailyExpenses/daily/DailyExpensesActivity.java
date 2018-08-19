package com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.georgi.allaboutthemoney.utils.CalendarActivity;
import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
import com.example.georgi.allaboutthemoney.R;

public class DailyExpensesActivity extends BaseDrawerActivity implements View.OnClickListener {
    public static final long IDENTIFIER = 388;
    private Button mSelectDayButton;
    private DailyExpensesFragment mDailyExpensesFragment;
    private TextView mDate;

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expenses);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mSelectDayButton = findViewById(R.id.day_selector);
        mDate = findViewById(R.id.selected_day);
        mSelectDayButton.setOnClickListener(this);
        Intent incoming = getIntent();
        String date = incoming.getStringExtra("date");
        mDate.setText(date);
        mDailyExpensesFragment = DailyExpensesFragment.getInstance();
        Bundle dateBundle = new Bundle();
        dateBundle.putString("date",date);
        mDailyExpensesFragment.setArguments(dateBundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.daily,mDailyExpensesFragment)
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
        intent.putExtra("identifier",String.valueOf(IDENTIFIER));
        startActivity(intent);
    }
}
