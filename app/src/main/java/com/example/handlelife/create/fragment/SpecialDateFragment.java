package com.example.handlelife.create.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.handlelife.R;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.text.SimpleDateFormat;

public class SpecialDateFragment extends Fragment {

    View rootView;

    TimePickerView timePickerView;
    //TextView tvTime;
    Context mContext;

    public SpecialDateFragment() {
        // Required empty public constructor
    }

    public static SpecialDateFragment newInstance() {
        SpecialDateFragment fragment = new SpecialDateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mContext = getActivity();

        timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //tvTime.setText(format.format(date));
            }
        }).build();
        timePickerView.show();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_special_date, container, false);
        }

        //tvTime = rootView.findViewById(R.id.special_date_selected);

        return rootView;
    }
}