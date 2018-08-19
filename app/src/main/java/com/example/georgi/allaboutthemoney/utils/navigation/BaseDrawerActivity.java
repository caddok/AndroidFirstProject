package com.example.georgi.allaboutthemoney.utils.navigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.daily.DailyExpensesActivity;
import com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.SetBudget.SetBudgetActivity;
import com.example.georgi.allaboutthemoney.Dreams.create.CreateDreamActivity;
import com.example.georgi.allaboutthemoney.Dreams.current.CurrentDreamActivity;
import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.Reports.daily.DailyExpensesReportActivity;
import com.example.georgi.allaboutthemoney.Reports.monthly.MonthlyExpensesReportActivity;
import com.example.georgi.allaboutthemoney.Reports.weekly.WeeklyExpensesReportActivity;
import com.example.georgi.allaboutthemoney.summary.MonthlySummary;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public abstract class BaseDrawerActivity extends AppCompatActivity {
    private Drawer mDrawer;

    private void setupDrawer() {
        SecondaryDrawerItem monthlySummaryItem = new SecondaryDrawerItem()
                .withIdentifier(MonthlySummary.IDENTIFIER)
                .withName("Monthly Summaries");
        //Setting up the budget
        SecondaryDrawerItem setBudgetItem = new SecondaryDrawerItem()
                .withIdentifier(SetBudgetActivity.IDENTIFIER)
                .withName(R.string.secondary_drawer_budget_set);


        SecondaryDrawerItem dailyExpensesItem = new SecondaryDrawerItem()
                .withIdentifier(DailyExpensesActivity.IDENTIFIER)
                .withName(R.string.secondary_drawer_budget_daily_exp);

        //Reports for daily, weekly and monthly expenses
        SecondaryDrawerItem dailyExpensesReportItem = new SecondaryDrawerItem()
                .withIdentifier(DailyExpensesReportActivity.IDENTIFIER)
                .withName(R.string.secondary_drawer_reports_daily);
        SecondaryDrawerItem weeklyExpensesItem = new SecondaryDrawerItem()
                .withIdentifier(WeeklyExpensesReportActivity.IDENTIFIER)
                .withName(R.string.secondary_drawer_reports_weekly);
        SecondaryDrawerItem monthlyExpensesItem = new SecondaryDrawerItem()
                .withIdentifier(MonthlyExpensesReportActivity.IDENTIFIER)
                .withName(R.string.secondary_drawer_reports_monthly);
        //Creating a dream or checking out a current one
        SecondaryDrawerItem createADreamItem = new SecondaryDrawerItem()
                .withIdentifier(CreateDreamActivity.IDENTIFIER)
                .withName(R.string.create);
        SecondaryDrawerItem checkDreamItem = new SecondaryDrawerItem()
                .withIdentifier(CurrentDreamActivity.IDENTIFIER)
                .withName(R.string.current);

        //TODO:Figure out how to take it from account login
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(getResources().getDrawable(R.drawable.header2,null))
                .addProfiles(new ProfileDrawerItem()
                        .withName("Georgi Delchev")
                        .withEmail("g.delchev93@gmail.com")
                        .withIcon(getResources().getDrawable(R.drawable.profile2,null)))
                .withOnAccountHeaderListener((view, profile, current) -> false)
                .build();

        mDrawer = new DrawerBuilder()
                .withAccountHeader(header)
                .withActivity(this)
                .withToolbar(getToolbar())
                .addDrawerItems(
                        monthlySummaryItem,
                        new DividerDrawerItem(),
                        setBudgetItem,
                        new DividerDrawerItem(),
                        dailyExpensesItem,
                        new DividerDrawerItem(),
                        dailyExpensesReportItem,
                        new DividerDrawerItem(),
                        weeklyExpensesItem,
                        new DividerDrawerItem(),
                        monthlyExpensesItem,
                        new DividerDrawerItem(),
                        createADreamItem,
                        new DividerDrawerItem(),
                        checkDreamItem
                )
                .withOnDrawerItemClickListener((view, position, drawerItem) -> setOnDrawerClickListener(drawerItem))
                .build();

    }

    private boolean setOnDrawerClickListener(IDrawerItem drawerItem) {
        long identifier = drawerItem.getIdentifier();
        Intent intent = null;
        if (getIdentifier() == identifier) {
            return false;
        }
        if (identifier == MonthlySummary.IDENTIFIER) {
            intent = new Intent(this,MonthlySummary.class);
        } else if (identifier == SetBudgetActivity.IDENTIFIER) {
            intent = new Intent(this,SetBudgetActivity.class);
        } else if (identifier == DailyExpensesActivity.IDENTIFIER) {
            intent = new Intent(this,DailyExpensesActivity.class);
        } else if (identifier == DailyExpensesReportActivity.IDENTIFIER) {
            intent = new Intent(this,DailyExpensesReportActivity.class);
        } else if (identifier == WeeklyExpensesReportActivity.IDENTIFIER) {
            intent = new Intent(this,WeeklyExpensesReportActivity.class);
        } else if (identifier == MonthlyExpensesReportActivity.IDENTIFIER) {
            intent = new Intent(this,MonthlyExpensesReportActivity.class);
        } else if (identifier == CreateDreamActivity.IDENTIFIER) {
            intent = new Intent(this,CreateDreamActivity.class);
        } else if (identifier == CurrentDreamActivity.IDENTIFIER) {
            intent = new Intent(this,CurrentDreamActivity.class);
        }
        if (intent == null) {
            return false;
        }
        startActivity(intent);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    protected abstract long getIdentifier();

    protected abstract Toolbar getToolbar();

}
