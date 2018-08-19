package com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.SetBudget;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.models.MonthlySummary;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxesFragment extends Fragment implements View.OnClickListener {
    private Button mButtonExpenses;
    private Spinner mExpensesSpinner;
    private ArrayAdapter<CharSequence> mExpensesSpinnerAdapter;
    private Repository<MonthlySummary> mSummaryRepository;
    private EditText mExpensesText;
    private String mSelectedMonth;


    public TaxesFragment() {
        // Required empty public constructor
    }

    public static TaxesFragment getInstance() {
        return new TaxesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taxes, container, false);
        mButtonExpenses = view.findViewById(R.id.enter_expenses);
        mExpensesSpinner = view.findViewById(R.id.spinner);
        mExpensesSpinnerAdapter = ArrayAdapter.createFromResource(getContext()
                ,R.array.expenses_options_array
                ,android.R.layout.simple_spinner_item);
        mExpensesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mExpensesSpinner.setAdapter(mExpensesSpinnerAdapter);
        mExpensesText = view.findViewById(R.id.expenses);
        mSummaryRepository = FirebaseRepository.getSummaryRepositoryInstance();
        mButtonExpenses.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int tax = Integer.parseInt(mExpensesText.getText().toString());
        mSummaryRepository.getAllFromQuery(summaries -> {
            for (MonthlySummary summary : summaries) {
                if (summary.month.equals(mSelectedMonth)) {
                    summary.taxes += tax;
                }
            }
        });
    }

    public void getSelectedMonth(String month) {
        mSelectedMonth = month;
    }
}
