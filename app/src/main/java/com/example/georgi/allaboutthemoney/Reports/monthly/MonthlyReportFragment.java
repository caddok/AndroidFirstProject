package com.example.georgi.allaboutthemoney.Reports.monthly;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.models.Expense;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlyReportFragment extends Fragment {
    private static final String NO_RECORD_TOAST = "No records for the selected month";
    private ListView mMonthlyReport;
    private int mQueryMonth;
    private ArrayAdapter<String> mMonthlyAdapter;
    private Repository<Expense> mExpenseRepository;

    public MonthlyReportFragment() {
        // Required empty public constructor
    }

    public static MonthlyReportFragment getInstance() {
        return new MonthlyReportFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_report, container, false);
        mMonthlyReport = view.findViewById(R.id.lv_monthly_report);
        mMonthlyAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        mMonthlyReport.setAdapter(mMonthlyAdapter);
        if (getArguments() != null){
            mQueryMonth = getArguments().getInt("monthNumber");
        }
        mExpenseRepository = FirebaseRepository.getExpenseRepositoryInstance();
        mExpenseRepository.getAllFromQuery(expenses -> {
            for (Expense expens : expenses) {
                if (expens.date != null) {
                    String[] parts = expens.date.split("-");
                    int monthAsNumber = Integer.parseInt(parts[1]);
                    if (monthAsNumber == mQueryMonth) {
                        mMonthlyAdapter.add(expens.toString());
                    }
                }
            }
        });
        return view;
    }

}
