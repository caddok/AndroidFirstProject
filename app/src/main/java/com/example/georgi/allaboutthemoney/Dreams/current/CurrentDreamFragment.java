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
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentDreamFragment extends Fragment {
    private ListView mDreamListView;
    private Repository<Dream> mDreamRepository;
    private ArrayAdapter<String> mDreamsAdapter;
    private TextView mDate;
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
        mDreamRepository = FirebaseRepository.getDreamRepositoryInstance();
        mDreamsAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        mDreamListView.setAdapter(mDreamsAdapter);
        mDreamRepository.getAllFromQuery(dreams->{
            for (Dream dream : dreams) {
                mDreamsAdapter.add(dream.toString());
            }
        });

        return view;
    }


}
