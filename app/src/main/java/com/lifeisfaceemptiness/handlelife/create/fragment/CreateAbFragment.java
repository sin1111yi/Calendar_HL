package com.lifeisfaceemptiness.handlelife.create.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;

import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.customui.InfoDialogButton;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

public class CreateAbFragment extends Fragment implements
        View.OnClickListener {

    private static final String TAG = "CreateAbFragment";
    private View rootView;

    private AppCompatImageButton setTypeIncome;
    private AppCompatImageButton setTypeBill;
    private AppCompatImageButton setTypeMedical;
    private AppCompatImageButton setTypeFood;
    private AppCompatImageButton setTypeStore;
    private AppCompatImageButton setTypeTravel;
    private AppCompatImageButton setTypeGift;
    private AppCompatImageButton setTypeLeisure;
    private AppCompatImageButton setTypeEducate;
    private AppCompatImageButton setTypeClothe;
    private AppCompatImageButton setTypeLifeServer;
    private AppCompatImageButton setTypeOther;

    private InfoDialogButton setAccount;
    private InfoDialogButton setDescription;
    private TextView tvSelectedType;
    private String selectedType;
    private EditText abDescription;

    private String[] accountTypesList;

    private Context mContext;

    private class AccountBook {
        public String Type;
        public String Account;
        //public float Account;
        public String Description;
    }

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

        initImageButtons();

        setAccount = rootView.findViewById(R.id.ab_set_account);
        setAccount.setBtnName(R.string.enter_account);
        setAccount.setDialogType(InfoDialogButton.InfoDialogType.ENTER_STRING);
        setAccount.setEnterStringDialogTitle(R.string.enter_account);

        setDescription = rootView.findViewById(R.id.ab_set_description);
        setDescription.setDialogType(InfoDialogButton.InfoDialogType.COMMON_INFO);
        setDescription.setCommonDialogMessage(getResources().getString(R.string.enter_below));
        setDescription.setBtnImage(R.drawable.ali_arrow_down);
        setDescription.setBtnName(R.string.enter_description);

        tvSelectedType = rootView.findViewById(R.id.account_book_type_selected);

        accountTypesList = getResources().getStringArray(R.array.account_types);
        selectedType = accountTypesList[11];// Others

        return rootView;
    }

    public void initImageButtons() {
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
    }


    @Override
    public void onClick(View v) {
        AllButtonBackgroundColorSet(R.color.white);
        if (v.getId() == R.id.ab_btn_income) {
            Log.d(TAG, "Income button clicked!");
            setTypeIncome.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[0];

        } else if (v.getId() == R.id.ab_btn_bill) {
            Log.d(TAG, "Bill button clicked!");
            setTypeBill.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[1];

        } else if (v.getId() == R.id.ab_btn_medical) {
            Log.d(TAG, "Medical button clicked!");
            setTypeMedical.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            Toast.makeText(mContext, getResources().
                    getString(R.string.health_wish), Toast.LENGTH_SHORT).show();
            selectedType = accountTypesList[2];

        } else if (v.getId() == R.id.ab_btn_food) {
            Log.d(TAG, "Food button clicked!");
            setTypeFood.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[3];

        } else if (v.getId() == R.id.ab_btn_store) {
            Log.d(TAG, "Store button clicked!");
            setTypeStore.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[4];

        } else if (v.getId() == R.id.ab_btn_travel) {
            Log.d(TAG, "Travel button clicked!");
            setTypeTravel.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[5];

        } else if (v.getId() == R.id.ab_btn_gift) {
            Log.d(TAG, "Gift button clicked!");
            setTypeGift.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[6];

        } else if (v.getId() == R.id.ab_btn_leisure) {
            Log.d(TAG, "Leisure button clicked!");
            setTypeLeisure.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[7];

        } else if (v.getId() == R.id.ab_btn_educate) {
            Log.d(TAG, "Educate button clicked!");
            setTypeEducate.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[8];

        } else if (v.getId() == R.id.ab_btn_clothe) {
            Log.d(TAG, "Clothe button clicked!");
            setTypeClothe.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[9];

        } else if (v.getId() == R.id.ab_btn_server) {
            Log.d(TAG, "LifeServer button clicked!");
            setTypeLifeServer.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[10];

        } else if (v.getId() == R.id.ab_btn_other) {
            Log.d(TAG, "Other button clicked!");
            setTypeOther.setBackgroundColor(getResources().
                    getColor(R.color.light_gray, mContext.getTheme()));
            selectedType = accountTypesList[11];

        }
        tvSelectedType.setText(selectedType);
        Log.d(TAG, "selectedType has been set to " + selectedType);
    }

    public void AllButtonBackgroundColorSet(int color) {
        int getColor = getResources().getColor(color, mContext.getTheme());
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

    private void SaveAllData() {
        AccountBook saved = new AccountBook();
        saved.Type = selectedType;
        saved.Account = setAccount.getBtnSet();
        saved.Description = abDescription.getText().toString();
    }

    @Override
    public void onPause() {
        super.onPause();
        SaveAllData();
    }
}