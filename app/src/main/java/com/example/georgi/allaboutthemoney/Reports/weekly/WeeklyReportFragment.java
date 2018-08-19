package com.example.georgi.allaboutthemoney.Reports.weekly;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.models.Expense;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyReportFragment extends Fragment {
    private ListView results;
    private ArrayAdapter<String> resultsAdapter;
    private String mQueryDate;
    private Repository<Expense> mExpenseRepository;

    public WeeklyReportFragment() {
        // Required empty public constructor
    }

    public static WeeklyReportFragment getInstance() {
        return new WeeklyReportFragment();
    }

    public void getDate(String date) {
        mQueryDate = date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekly_report, container, false);
        results = view.findViewById(R.id.lv_weekly_expenses);
        resultsAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        results.setAdapter(resultsAdapter);
        mExpenseRepository = FirebaseRepository.getExpenseRepositoryInstance();

        mExpenseRepository.getAllFromQuery(expenses-> {
            int monday = Integer.parseInt(findMonday(mQueryDate));
            int sunday = monday + 6;
            for (Expense expense : expenses) {
                int currentDay = transformDayOfWeekToInt(expense);
                if (currentDay >= monday && currentDay <= sunday) {
                    resultsAdapter.add(expense.toString());
                }
            }
        });
        return view;
    }

    private int transformDayOfWeekToInt(Expense expense) {
        String[] fullDate = expense.date.split("-");
        return Integer.parseInt(fullDate[0]);
    }

    private String findMonday(String date) {
        String monday = "";
        int currentDay = 0;
        if (date != null) {
            String[] partsOfTheDate = date.split("-");
            currentDay = Integer.parseInt(partsOfTheDate[0]);
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        String selectedDay = dayFormat.format(calendar.getTime());

        switch (selectedDay) {
            case "Monday":
                monday += String.valueOf(currentDay);
                break;
            case "Tuesday":
                currentDay -= 1;
                monday += String.valueOf(currentDay);
                break;
            case "Wednesday":
                currentDay -= 2;
                monday += String.valueOf(currentDay);
                break;
            case "Thursday":
                currentDay -= 3;
                monday += String.valueOf(currentDay);
                break;
            case "Friday":
                currentDay -= 4;
                monday += String.valueOf(currentDay);
                break;
            case "Saturday":
                currentDay -= 5;
                monday += String.valueOf(currentDay);
                break;
            case "Sunday":
                currentDay -= 6;
                monday += String.valueOf(currentDay);
                break;
        }

        return monday;
    }

}
