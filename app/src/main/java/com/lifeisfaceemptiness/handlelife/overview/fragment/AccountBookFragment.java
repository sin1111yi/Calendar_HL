package com.lifeisfaceemptiness.handlelife.overview.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.lifeisfaceemptiness.handlelife.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AccountBookFragment extends Fragment implements
        View.OnClickListener {

    private static final String TAG = "AccountBookFragment";
    private View rootView;

    private Button selectDate;
    private Button selectType;
    private TextView tvSelectedDate;
    private TextView tvSelectedType;

    private String selectedDate;
    private String selectedType;

    WheelView typeSelectWheel;
    List<String> types = new ArrayList<>();
    ArrayWheelAdapter<String> arrayWheelAdapter;

    private Context mContext;

    public AccountBookFragment() {
        // Required empty public constructor
    }

    public static AccountBookFragment newInstance() {
        AccountBookFragment fragment = new AccountBookFragment();
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
            rootView = inflater.inflate(R.layout.fragment_ab_show, container, false);
        }

        selectDate = rootView.findViewById(R.id.ab_btn_select_date);
        selectType = rootView.findViewById(R.id.ab_btn_select_type);

        tvSelectedDate = rootView.findViewById(R.id.ab_selected_date);
        tvSelectedType = rootView.findViewById(R.id.ab_selected_type);

        selectDate.setOnClickListener(this);
        selectType.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ab_btn_select_date) {
            Log.d(TAG, "select date through time picker");
            TimePickerView timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                public void onTimeSelect(Date date, View v) {
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat format = new SimpleDateFormat(getResources().getString(R.string.date_format));
                    selectedDate = format.format(date);
                    tvSelectedDate.setText(selectedDate);
                    Log.d(TAG, format.format(date));
                }
            })// set style
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setTitleText(getResources().getString(R.string.select_date))
                    .isDialog(true)
                    .setOutSideCancelable(true)
                    .build();
            timePickerView.show();

        } else if (v.getId() == R.id.ab_btn_select_type) {
            Log.d(TAG, "select account type");
            View view = getLayoutInflater().inflate(R.layout.wheelview_dialog, null);

            types = Arrays.asList(getResources().getStringArray(R.array.account_types));
            typeSelectWheel = view.findViewById(R.id.wv_dialog_view);
            typeSelectWheel.setCyclic(false);
            typeSelectWheel.setAdapter(new ArrayWheelAdapter<>(types));

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setView(view);
            builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    typeSelectWheel.setOnItemSelectedListener(new OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(int index) {
                            selectedType = types.get(index);
                            tvSelectedType.setText(selectedType);
                            Log.d(TAG, "Selected type:" + selectedType);
                        }
                    });
                }
            });
            builder.create().show();
        }
    }
}