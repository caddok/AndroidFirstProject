package com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.SetBudget;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.georgi.allaboutthemoney.utils.navigation.BaseDrawerActivity;
import com.example.georgi.allaboutthemoney.R;
//TODO:Connect with MonthlySummary to display the summary
//TODO:Also add another collection in the firebase
public class SetBudgetActivity extends BaseDrawerActivity {
    public static final long IDENTIFIER = 706;
    private Toolbar mToolbar;
    private SetBudgetFragment mSetBudgetFragment;
    private TaxesFragment mTaxesFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mSetBudgetFragment = SetBudgetFragment.getInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content,mSetBudgetFragment)
                .commit();
        mTaxesFragment = TaxesFragment.getInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.taxes_fragment,mTaxesFragment)
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
