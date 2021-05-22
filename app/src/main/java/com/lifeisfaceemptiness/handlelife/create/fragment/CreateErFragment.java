package com.lifeisfaceemptiness.handlelife.create.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lifeisfaceemptiness.handlelife.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateErFragment extends Fragment implements
        View.OnClickListener {

    View rootView;

    private final String TAG = "CreateReFragment";

    private Button callTimePicker;
    private TextView tvSelectTip;
    private TextView tvSelectedTime;
    private EditText etEventTitle;

    private String selectedDate;

    Context mContext;

    public CreateErFragment() {
        // Required empty public constructor
    }

    public static CreateErFragment newInstance() {
        CreateErFragment fragment = new CreateErFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mContext=getActivity();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_er_create, container, false);
        }

        etEventTitle=rootView.findViewById(R.id.er_create_editText);
        callTimePicker=rootView.findViewById(R.id.er_btn_select_date);
        tvSelectTip = rootView.findViewById(R.id.er_select_tip);
        tvSelectedTime = rootView.findViewById(R.id.er_selected_time);
        tvSelectTip.setText(getResources().getText(R.string.select_time));

        callTimePicker.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.er_btn_select_date) {
            Log.d(TAG, "select date through time picker");
            TimePickerView timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                public void onTimeSelect(Date date, View v) {
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat format = new SimpleDateFormat(getResources().getString(R.string.time_format));
                    selectedDate = format.format(date);
                    tvSelectedTime.setText(selectedDate);
                    Log.d(TAG, format.format(date));
                }
            })// set style
                    .setType(new boolean[]{true, true, true, true, true, true})
                    .setTitleText(getResources().getString(R.string.select_date))
                    .isDialog(true)
                    .setOutSideCancelable(true)
                    .build();
            timePickerView.show();
        }
    }
}