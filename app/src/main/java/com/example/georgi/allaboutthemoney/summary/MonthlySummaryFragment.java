package com.example.georgi.allaboutthemoney.summary;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.models.MonthlySummary;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlySummaryFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String NO_RECORD_TOAST = "No record for this month";
    private TextView mMonthlyIncomePresetText;
    private TextView mIncomeDisplayText;
    private TextView mTaxesMoneyPreset;
    private TextView mTaxesDisplayText;
    private TextView mPerDayPreset;
    private TextView mMoneyPerDayDisplayText;

    private Spinner mMonthsSpinner;
    private ArrayAdapter<CharSequence> mMonthsAdapter;
    private Repository<MonthlySummary> mSummaryRepository;

    public MonthlySummaryFragment() {
        // Required empty public constructor
    }

    public static MonthlySummaryFragment getInstance() {
        return new MonthlySummaryFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_summary, container, false);

        mMonthsSpinner = view.findViewById(R.id.spinner_summary);
        mMonthsAdapter = ArrayAdapter.createFromResource(getContext()
                , R.array.months,
                android.R.layout.simple_spinner_item);
        mMonthsSpinner.setAdapter(mMonthsAdapter);
        mMonthlyIncomePresetText = view.findViewById(R.id.income_tv);
        mIncomeDisplayText = view.findViewById(R.id.income_summary);
        mTaxesMoneyPreset = view.findViewById(R.id.tv_taxes);
        mTaxesDisplayText = view.findViewById(R.id.taxes_summary);
        mPerDayPreset = view.findViewById(R.id.tv_funds);
        mMoneyPerDayDisplayText = view.findViewById(R.id.funds_per_day);
        mSummaryRepository = FirebaseRepository.getSummaryRepositoryInstance();
        mMonthsSpinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final boolean[] isDisplayed = {false};
        String month = String.valueOf(mMonthsSpinner.getSelectedItem());
        mSummaryRepository.getAllFromQuery(monthlysummarys -> {
            for (MonthlySummary summary : monthlysummarys) {
                if (summary.month.equals(month)) {
                    mIncomeDisplayText.setText(String.valueOf(summary.income));
                    mTaxesDisplayText.setText(String.valueOf(summary.taxes));
                    int perDay = calcFundsPerDay(month, summary.income, summary.taxes);
                    mMoneyPerDayDisplayText.setText(String.valueOf(perDay));
                    isDisplayed[0] = true;
                    break;
                }
            }
        });
        if (!isDisplayed[0]) {
            Toast.makeText(getContext(), NO_RECORD_TOAST, Toast.LENGTH_SHORT).show();
        }
    }

    private int calcFundsPerDay(String month, int income, int taxes) {
        ArrayList<String> monthsWith30Days = new ArrayList<>();
        monthsWith30Days.add("April");
        monthsWith30Days.add("June");
        monthsWith30Days.add("September");
        monthsWith30Days.add("November");
        int perDay = 0;
        if (month.equals("February")) {
            perDay = (income - taxes) / 28;
        } else if (monthsWith30Days.contains(month)) {
            perDay = (income - taxes) / 30;
        } else {
            perDay = (income - taxes) / 31;
        }
        return perDay;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
