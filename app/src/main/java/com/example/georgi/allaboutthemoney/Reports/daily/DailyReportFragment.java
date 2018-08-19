package com.example.georgi.allaboutthemoney.Reports.daily;


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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyReportFragment extends Fragment {
    private static final String EMPTY_ADAPTER_TOAST = "No records for the current day";
    private String mQueryDate;
    private ListView mExpensesList;
    private ArrayAdapter<String> mExpensesListAdapter;
    private Repository<Expense> mExpenseRepository;

    public DailyReportFragment() {
        // Required empty public constructor
    }
    public static DailyReportFragment getInstance(){
        return new DailyReportFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_report, container, false);
        mExpensesList = view.findViewById(R.id.lv_daily_expenses);
        mExpensesListAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        mExpensesList.setAdapter(mExpensesListAdapter);
        mExpenseRepository = FirebaseRepository.getExpenseRepositoryInstance();
        mExpenseRepository.getAllFromQuery(expenses -> {
            for (Expense expense : expenses) {
                if (expense.date.equals(mQueryDate)) {
                    mExpensesListAdapter.add(expense.toString());
                }
                if (mExpensesListAdapter.isEmpty()) {
                    Toast.makeText(getContext(), EMPTY_ADAPTER_TOAST, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void getDate(String date) {
        mQueryDate = date;
    }

}
