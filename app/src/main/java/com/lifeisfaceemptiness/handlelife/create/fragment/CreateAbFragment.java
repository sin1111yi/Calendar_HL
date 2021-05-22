package com.lifeisfaceemptiness.handlelife.create.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageButton;

import com.lifeisfaceemptiness.handlelife.R;

public class CreateAbFragment extends Fragment implements
        View.OnClickListener {

    private static final String TAG = "CreateAbFragment";
    View rootView;

    AppCompatImageButton setTypeIncome;
    AppCompatImageButton setTypeBill;
    AppCompatImageButton setTypeMedical;
    AppCompatImageButton setTypeFood;
    AppCompatImageButton setTypeStore;
    AppCompatImageButton setTypeTravel;
    AppCompatImageButton setTypeGift;
    AppCompatImageButton setTypeMovie;

    Context mContext;

    boolean btnSelected = false;

    public CreateAbFragment() {
        // Required empty public constructor
    }

    public static CreateAbFragment newInstance() {
        CreateAbFragment fragment = new CreateAbFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_ab_create, container, false);
        }

        setTypeIncome = rootView.findViewById(R.id.ab_btn_income);
        setTypeBill = rootView.findViewById(R.id.ab_btn_bill);
        setTypeMedical = rootView.findViewById(R.id.ab_btn_medical);
        setTypeFood = rootView.findViewById(R.id.ab_btn_food);
        setTypeStore = rootView.findViewById(R.id.ab_btn_store);
        setTypeTravel = rootView.findViewById(R.id.ab_btn_travel);
        setTypeGift = rootView.findViewById(R.id.ab_btn_gift);
        setTypeMovie = rootView.findViewById(R.id.ab_btn_movie);

        setTypeIncome.setOnClickListener(this);
        setTypeBill.setOnClickListener(this);
        setTypeMedical.setOnClickListener(this);
        setTypeFood.setOnClickListener(this);
        setTypeStore.setOnClickListener(this);
        setTypeTravel.setOnClickListener(this);
        setTypeGift.setOnClickListener(this);
        setTypeMovie.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ab_btn_income) {
            Log.d(TAG, "Income button clicked!");
        } else if (v.getId() == R.id.ab_btn_bill) {
            Log.d(TAG, "Bill button clicked!");
        } else if (v.getId() == R.id.ab_btn_medical) {
            Log.d(TAG, "Medical button clicked!");
        } else if (v.getId() == R.id.ab_btn_food) {
            Log.d(TAG, "Food button clicked!");
        } else if (v.getId() == R.id.ab_btn_store) {
            Log.d(TAG, "Store button clicked!");
        } else if (v.getId() == R.id.ab_btn_travel) {
            Log.d(TAG, "Travel button clicked!");
        } else if (v.getId() == R.id.ab_btn_gift) {
            Log.d(TAG, "Gift button clicked!");
        } else if (v.getId() == R.id.ab_btn_movie) {
            Log.d(TAG, "Movie button clicked!");
        }

    }

    public void imgButtonToggleBackground() {

    }
}