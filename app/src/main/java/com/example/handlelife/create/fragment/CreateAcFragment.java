package com.example.handlelife.create.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handlelife.R;
import com.example.handlelife.customui.FragmentTransfer;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CreateAcFragment extends Fragment {

    private static final String TAG = "AlarmClockList";
    View rootView;
    FloatingActionButton createSelfItem;
    FragmentTransfer fragmentTransfer;

    public CreateAcFragment() {
        // Required empty public constructor
    }

    public static CreateAcFragment newInstance(String param1, String param2) {
        CreateAcFragment fragment = new CreateAcFragment();
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
            rootView = inflater.inflate(R.layout.fragment_create_ac, container, false);
        }

        createSelfItem = rootView.findViewById(R.id.create_alarm_clock);
        createSelfItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return rootView;
    }

    public void setFragmentTransfer(FragmentTransfer FT) {
        this.fragmentTransfer = FT;
    }
}