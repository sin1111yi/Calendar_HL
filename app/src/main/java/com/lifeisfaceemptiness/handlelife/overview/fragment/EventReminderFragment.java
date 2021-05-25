package com.lifeisfaceemptiness.handlelife.overview.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifeisfaceemptiness.handlelife.R;


public class EventReminderFragment extends Fragment {

    View rootView;

    public EventReminderFragment() {
        // Required empty public constructor
    }

    public static EventReminderFragment newInstance() {
        EventReminderFragment fragment = new EventReminderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_er_show, container, false);
        }
        return rootView;
    }
}