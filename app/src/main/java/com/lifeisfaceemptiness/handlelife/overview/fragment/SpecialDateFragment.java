package com.lifeisfaceemptiness.handlelife.overview.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.lifeisfaceemptiness.handlelife.R;

import org.jetbrains.annotations.NotNull;

public class SpecialDateFragment extends Fragment {

    View rootView;

    Context mContext;
    CalendarView calendarView;

    //TODO: 现在这个Fragment中CalendarView被选中的日期背景色为黑色，应该为深灰色
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

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_sd_show, container, false);
        }

        calendarView=rootView.findViewById(R.id.sd_calendar);

        return rootView;
    }



}