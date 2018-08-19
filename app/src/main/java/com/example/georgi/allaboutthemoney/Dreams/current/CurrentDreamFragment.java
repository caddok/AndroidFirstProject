package com.example.georgi.allaboutthemoney.Dreams.current;


import android.app.Fragment;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.models.Dream;
import com.example.georgi.allaboutthemoney.utils.models.Expense;
import com.example.georgi.allaboutthemoney.utils.models.MonthlySummary;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentDreamFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView mDreamListView;
    private Repository<Dream> mDreamRepository;
    private ArrayAdapter<String> mDreamsAdapter;
    private TextView mDate;
    private Repository<MonthlySummary> summaryRepository;
    private Repository<Expense> expenseRepository;
    private String moneyLeft;
    public CurrentDreamFragment() {
        // Required empty public constructor
    }

    public static CurrentDreamFragment getInstance() {
        return new CurrentDreamFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_dream, container, false);
        mDreamListView = view.findViewById(R.id.lv_dreams);
        mDate = view.findViewById(R.id.money_left);
        String currentDate = (new SimpleDateFormat("dd-M-yyyy").format(Calendar.getInstance().getTime()));
        moneyLeft = calcMoneyLeft(currentDate);
        mDate.setText("You have " + moneyLeft +" left for today");
        mDreamRepository = FirebaseRepository.getDreamRepositoryInstance();
        mDreamsAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        mDreamListView.setAdapter(mDreamsAdapter);
        mDreamRepository.getAllFromQuery(dreams->{
            for (Dream dream : dreams) {
                mDreamsAdapter.add(dream.toString());
            }
        });
        mDreamListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dream selected = (Dream) parent.getSelectedItem();
        selected.progress += Double.parseDouble(moneyLeft);
    }

    private String calcMoneyLeft(String date) {
        String moneyLeft = null;
        summaryRepository = FirebaseRepository.getSummaryRepositoryInstance();
        expenseRepository = FirebaseRepository.getExpenseRepositoryInstance();
        final int[] perDay = {0};
        String[] splitDate = date.split("-");
        summaryRepository.getAllFromQuery(monthlySummaries -> {
            for (MonthlySummary monthlySummary : monthlySummaries) {
                if (monthlySummary.month.equals(splitDate[1])) {
                    int monthAsNumber = Integer.parseInt(splitDate[1]);
                    if (monthAsNumber == 2) {
                        perDay[0] = monthlySummary.income - monthlySummary.taxes / 28;
                    } else if (monthAsNumber == 4 || monthAsNumber == 6
                            || monthAsNumber == 9 || monthAsNumber == 11) {
                        perDay[0] = monthlySummary.income - monthlySummary.taxes / 30;
                    } else {
                        perDay[0] = monthlySummary.income - monthlySummary.taxes / 31;
                    }
                }
            }
        });
        final int[] spendingForToday = {0};
        expenseRepository.getAllFromQuery(expenses -> {
            for (Expense expens : expenses) {
                if (expens.date.equals(date)) {
                    spendingForToday[0] += expens.cost;
                }
            }
        });
        moneyLeft = String.valueOf((perDay[0] - spendingForToday[0]));
        return moneyLeft;
    }
}
