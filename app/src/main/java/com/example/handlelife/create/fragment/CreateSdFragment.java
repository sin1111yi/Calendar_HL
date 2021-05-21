package com.example.handlelife.create.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.handlelife.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateSdFragment extends Fragment implements
        View.OnClickListener {

    private final String TAG = "CreateSdFragment";

    private View rootView;
    private Button callTimePicker;
    private TextView tvSelectTip;
    private TextView tvSelectedDate;

    private String selectedDate;

    Context mContext;

    public CreateSdFragment() {
        // Required empty public constructor
    }

    public static CreateSdFragment newInstance() {
        CreateSdFragment fragment = new CreateSdFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_sd_create, container, false);
        }

        callTimePicker = rootView.findViewById(R.id.sd_btn_select_date);
        tvSelectTip = rootView.findViewById(R.id.sd_select_tip);
        tvSelectedDate = rootView.findViewById(R.id.sd_selected_date);
        tvSelectTip.setText(getResources().getText(R.string.select_date));

        callTimePicker.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sd_btn_select_date) {
            Log.d(TAG, "select date through time picker");
            TimePickerView timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                @SuppressLint("SimpleDateFormat")
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat(getResources().getString(R.string.date_format));
                    tvSelectedDate.setText(format.format(date));
                    Log.d(TAG, format.format(date));
                }
            }).build();
            timePickerView.show();
        }
    }

}