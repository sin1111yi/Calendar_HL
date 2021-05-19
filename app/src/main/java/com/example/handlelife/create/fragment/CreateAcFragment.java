package com.example.handlelife.create.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handlelife.R;
import com.example.handlelife.customui.FragmentTransfer;

public class CreateAcFragment extends Fragment {

    private static final String TAG = "AlarmClockList";
    View rootView;
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

        return rootView;
    }

    public void setFragmentTransfer(FragmentTransfer FT) {
        this.fragmentTransfer = FT;
    }
}