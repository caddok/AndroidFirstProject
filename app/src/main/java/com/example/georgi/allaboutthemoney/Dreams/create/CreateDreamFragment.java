package com.example.georgi.allaboutthemoney.Dreams.create;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georgi.allaboutthemoney.R;
import com.example.georgi.allaboutthemoney.utils.models.Dream;
import com.example.georgi.allaboutthemoney.utils.repository.FirebaseRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateDreamFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final String NO_PRICE_TOAST = "Enter price first";
    private static final String NO_NAME_TOAST = "Enter name first";
    private static final String NO_CATEGORY_TOAST = "Select category first";
    private static final String NO_PERIOD_TOAST = "Select period first";

    private Spinner mDreamCategoriesSpinner;
    private ArrayAdapter<CharSequence> mDreamCategoriesAdapter;

    private EditText mDreamPrice;
    private EditText mDreamName;

    private Spinner mPeriodSpinner;
    private ArrayAdapter<CharSequence> mPeriodAdapter;
    private FirebaseRepository<Dream> mDreamFirebaseRepository;
    private Button mButtonCreate;
    private Dream dream;

    public CreateDreamFragment() {
        // Required empty public constructor
    }

    public static CreateDreamFragment getInstance(){
        return new CreateDreamFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create__dream_, container, false);

        mDreamCategoriesSpinner = view.findViewById(R.id.spinner_dream_categories);
        mDreamCategoriesAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.dream_categories,
                android.R.layout.simple_spinner_item);
        mDreamCategoriesSpinner.setAdapter(mDreamCategoriesAdapter);
        mDreamCategoriesSpinner.setOnItemSelectedListener(this);

        mButtonCreate = view.findViewById(R.id.create_dream);
        mButtonCreate.setOnClickListener(this);
        mDreamPrice = view.findViewById(R.id.dream_cost);
        mDreamName = view.findViewById(R.id.dream_name);
        mDreamFirebaseRepository = FirebaseRepository.getDreamRepositoryInstance();
        mPeriodSpinner = view.findViewById(R.id.period);
        mPeriodAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.saving_periods,
                android.R.layout.simple_spinner_item);
        mPeriodSpinner.setAdapter(mPeriodAdapter);
        dream = new Dream();
        mPeriodSpinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String dreamPriceText = String.valueOf(mDreamPrice.getText());
        if (dreamPriceText.equals("")) {
            Toast.makeText(getContext(),NO_PRICE_TOAST , Toast.LENGTH_SHORT).show();
        } else if (mDreamName.getText().toString().equals("")) {
            Toast.makeText(getContext(), NO_NAME_TOAST, Toast.LENGTH_SHORT).show();
        } else if (mDreamCategoriesSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), NO_CATEGORY_TOAST, Toast.LENGTH_SHORT).show();
        } else if (mPeriodSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), NO_PERIOD_TOAST, Toast.LENGTH_SHORT).show();
        } else {
            dream.cost = Double.parseDouble(dreamPriceText);
            dream.name = String.valueOf(mDreamName.getText());
            dream.progress = 0.0;
            mDreamFirebaseRepository.addDream(dream,newDream->{

            });
            dream = new Dream();
            mDreamName.setText("");
            mDreamPrice.setText("");
            mDreamCategoriesSpinner.setSelection(0);
            mPeriodSpinner.setSelection(0);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.spinner_dream_categories:
                dream.category = String.valueOf(mDreamCategoriesSpinner.getItemAtPosition(position));
                break;
            case R.id.period:
                dream.period = String.valueOf(mPeriodSpinner.getItemIdAtPosition(position));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
