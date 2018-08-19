package com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.SetBudget;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.models.MonthlySummary;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;

/**
 * A simple {@link Fragment} subclass.
 */
//TODO:Needs reworking because of main activity
public class SetBudgetFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final String ALREADY_EXISTING_RECORD_TOAST = "Select another month";
    private static final String NO_INCOME_INPUT_TOAST = "Enter income first";
    private Spinner mMonthsSpinner;
    private ArrayAdapter<CharSequence> mMonthsAdapter;
    private Button mButtonIncome;
    private EditText mIncomeText;
    private Repository<MonthlySummary> mSummaryRepository;
    private String month;
    private TaxesFragment mTaxesFragment;

    public SetBudgetFragment() {
        // Required empty public constructor
    }
    public static SetBudgetFragment getInstance() {
        return new SetBudgetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_budget, container, false);
        mButtonIncome = view.findViewById(R.id.btn_income);
        month = "";
        mIncomeText = view.findViewById(R.id.income);
        mMonthsSpinner = view.findViewById(R.id.month_selector);
        mMonthsAdapter = ArrayAdapter.createFromResource(getContext()
                ,R.array.months,
                android.R.layout.simple_spinner_item);
        mSummaryRepository = FirebaseRepository.getSummaryRepositoryInstance();
        mMonthsSpinner.setAdapter(mMonthsAdapter);
        mMonthsSpinner.setOnItemSelectedListener(this);
        mButtonIncome.setOnClickListener(this);
        mTaxesFragment = TaxesFragment.getInstance();
        mTaxesFragment.getSelectedMonth(month);
        return view;
    }

    @Override
    public void onClick(View v) {
        int income = Integer.parseInt(mIncomeText.getText().toString());
        if (income == 0) {
            Toast.makeText(getContext(), NO_INCOME_INPUT_TOAST, Toast.LENGTH_SHORT).show();
        } else {
            mSummaryRepository.getAllFromQuery(summaries -> {
                for (MonthlySummary summary : summaries) {
                    if (summary.income > 0) {
                        break;
                    }
                    if (summary.month.equals(month)) {
                        summary.income = income;
                    }
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final boolean[] isExistingRecord = {false};
        month = String.valueOf(mMonthsSpinner.getSelectedItem());
        mSummaryRepository.getAllFromQuery(monthlysummarys-> {
            for (MonthlySummary monthlysummary : monthlysummarys) {
                if (monthlysummary.month.equals(month)) {
                    Toast.makeText(getContext(), ALREADY_EXISTING_RECORD_TOAST, Toast.LENGTH_SHORT).show();
                    isExistingRecord[0] = true;
                    break;
                }
            }
        });
        if (!isExistingRecord[0]) {
            mSummaryRepository.addDream(new MonthlySummary(month), newSummary->{

            });
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
