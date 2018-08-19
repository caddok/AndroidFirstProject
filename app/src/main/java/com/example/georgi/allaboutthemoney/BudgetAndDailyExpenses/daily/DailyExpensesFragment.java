package com.example.georgi.allaboutthemoney.BudgetAndDailyExpenses.daily;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.CalendarActivity;
import com.example.georgi.allaboutthemoney.utils.models.Expense;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;
import com.example.georgi.allaboutthemoney.utils.repository.base.Repository;

/**
 * A simple {@link Fragment} subclass.
 */
//TODO:Check if work is done tomorrow;possible problem ""(the empty string may be with null value)
public class DailyExpensesFragment extends Fragment implements View.OnClickListener{
    private static final String NO_CATEGORY_SELECTED_TOAST_MESSAGE = "Select category first";
    private static final String NO_COST_ENTERED_TOAST_MESSAGE = "Fill the cost first";
    private static final String EMPTY_ENTRY_TOAST_MESSAGE = "Fill the blank fields first";
    private Spinner mDailyExpensesSpinner;
    private ArrayAdapter<CharSequence> mDailyExpensesAdapter;
    private EditText mExpenseCost;
    private Button mEnterExpenseButton;
    private Repository mExpenseRepository;
    private String mSelectedDate;

    public DailyExpensesFragment() {
        // Required empty public constructor
    }

    public static DailyExpensesFragment getInstance(){
        return new DailyExpensesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_expenses, container, false);
        mSelectedDate = getArguments().getString("date");
        mDailyExpensesSpinner = view.findViewById(R.id.daily_spinner);
        mDailyExpensesAdapter = ArrayAdapter.createFromResource(getContext()
                ,R.array.daily_expenses_options_array
                ,android.R.layout.simple_spinner_item);
        mDailyExpensesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDailyExpensesSpinner.setAdapter(mDailyExpensesAdapter);
        mExpenseCost = view.findViewById(R.id.cost);
        mEnterExpenseButton = view.findViewById(R.id.enter);
        mEnterExpenseButton.setOnClickListener(this);
        mExpenseRepository = FirebaseRepository.getExpenseRepositoryInstance();

        return view;
    }

    @Override
    public void onClick(View v) {
        String expenseCostText = String.valueOf(mExpenseCost.getText());
        String category = mDailyExpensesSpinner.getSelectedItem().toString();
        if (expenseCostText.equals("")) {
            Toast.makeText(getContext(),NO_COST_ENTERED_TOAST_MESSAGE,Toast.LENGTH_SHORT).show();
        } else if (category.equals("")) {
            Toast.makeText(getContext(),NO_CATEGORY_SELECTED_TOAST_MESSAGE,Toast.LENGTH_SHORT).show();
        } else if (category.equals("") && expenseCostText.equals("")) {
            Toast.makeText(getContext(),EMPTY_ENTRY_TOAST_MESSAGE,Toast.LENGTH_SHORT).show();
        } else {

            mExpenseRepository.addExpense(new Expense(mSelectedDate
                            ,category
                            ,Integer.valueOf(expenseCostText))
                    ,newExpense -> { });
            mDailyExpensesSpinner.setSelection(0);
            mExpenseCost.setText("");
        }

    }

}
