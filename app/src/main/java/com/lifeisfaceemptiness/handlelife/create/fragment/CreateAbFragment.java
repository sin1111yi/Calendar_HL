package com.lifeisfaceemptiness.handlelife.create.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    AppCompatImageButton setTypeLeisure;
    AppCompatImageButton setTypeEducate;
    AppCompatImageButton setTypeClothe;
    AppCompatImageButton setTypeLifeServer;
    AppCompatImageButton setTypeOther;

    Context mContext;

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
        mContext = getActivity();
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
        setTypeLeisure = rootView.findViewById(R.id.ab_btn_leisure);
        setTypeEducate = rootView.findViewById(R.id.ab_btn_educate);
        setTypeClothe = rootView.findViewById(R.id.ab_btn_clothe);
        setTypeLifeServer = rootView.findViewById(R.id.ab_btn_server);
        setTypeOther = rootView.findViewById(R.id.ab_btn_other);

        setTypeIncome.setOnClickListener(this);
        setTypeBill.setOnClickListener(this);
        setTypeMedical.setOnClickListener(this);
        setTypeFood.setOnClickListener(this);
        setTypeStore.setOnClickListener(this);
        setTypeTravel.setOnClickListener(this);
        setTypeGift.setOnClickListener(this);
        setTypeLeisure.setOnClickListener(this);
        setTypeEducate.setOnClickListener(this);
        setTypeClothe.setOnClickListener(this);
        setTypeLifeServer.setOnClickListener(this);
        setTypeOther.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View v) {
        AllButtonBackgroundColorSet(R.color.white);
        if (v.getId() == R.id.ab_btn_income) {
            Log.d(TAG, "Income button clicked!");
            setTypeIncome.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_bill) {
            Log.d(TAG, "Bill button clicked!");
            setTypeBill.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_medical) {
            Log.d(TAG, "Medical button clicked!");
            setTypeMedical.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            Toast.makeText(mContext,getResources().
                    getString(R.string.health_wish), Toast.LENGTH_SHORT).show();

        } else if (v.getId() == R.id.ab_btn_food) {
            Log.d(TAG, "Food button clicked!");
            setTypeFood.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_store) {
            Log.d(TAG, "Store button clicked!");
            setTypeStore.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_travel) {
            Log.d(TAG, "Travel button clicked!");
            setTypeTravel.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_gift) {
            Log.d(TAG, "Gift button clicked!");
            setTypeGift.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_leisure) {
            Log.d(TAG, "Leisure button clicked!");
            setTypeLeisure.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_educate) {
            Log.d(TAG, "Educate button clicked!");
            setTypeEducate.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_clothe) {
            Log.d(TAG, "Clothe button clicked!");
            setTypeClothe.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_server) {
            Log.d(TAG, "LifeServer button clicked!");
            setTypeLifeServer.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        } else if (v.getId() == R.id.ab_btn_other) {
            Log.d(TAG, "Other button clicked!");
            setTypeOther.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));

        }
    }

    public void AllButtonBackgroundColorSet(int color) {
        int getColor=getResources().getColor(color, mContext.getTheme());
        setTypeIncome.setBackgroundColor(getColor);
        setTypeBill.setBackgroundColor(getColor);
        setTypeMedical.setBackgroundColor(getColor);
        setTypeFood.setBackgroundColor(getColor);
        setTypeStore.setBackgroundColor(getColor);
        setTypeTravel.setBackgroundColor(getColor);
        setTypeGift.setBackgroundColor(getColor);
        setTypeLeisure.setBackgroundColor(getColor);
        setTypeEducate.setBackgroundColor(getColor);
        setTypeClothe.setBackgroundColor(getColor);
        setTypeLifeServer.setBackgroundColor(getColor);
        setTypeOther.setBackgroundColor(getColor);
    }

}