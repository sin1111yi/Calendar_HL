package com.example.handlelife.customui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.handlelife.R;

import java.util.ArrayList;
import java.util.List;

public class InfoDialogButton extends RelativeLayout {

    private InfoDialogType DialogType = InfoDialogType.UNSET_TYPE;
    private TextView btnName;
    private TextView btnSet;
    private Button btnDialog;
    private String getBtnName;
    private String getBtnSet;

    List<String> getSelected = new ArrayList<>();
    private String[] itemToShow;
    private int itemNum;
    private String dialogTitle;

    Context mContext;

    private AlertDialog.Builder dialogBuilder;

    public enum InfoDialogType {
        UNSET_TYPE,
        COMMON_INFO,
        MULTI_SELECT
    }

    private final String TAG = "InfoDialogButton";

    public InfoDialogButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.button_with_info, this);
        this.mContext = context;

        this.btnName = findViewById(R.id.btn_info_option_name);
        this.btnSet = findViewById(R.id.btn_info_option_set);
        this.btnDialog = findViewById(R.id.btn_start_dialog);
        this.btnDialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoButtonClickEvent();
            }
        });
        Log.d(TAG, "new InfoDialogButton has been set");
    }

    public void setDialogType(InfoDialogType dialogType) {
        this.DialogType = dialogType;
    }

    public void setBtnName(int nameId) {
        this.getBtnName = getResources().getString(nameId);
        this.btnName.setText(getBtnName);
    }

    public void setBtnSet(int setId) {
        this.getBtnSet = getResources().getString(setId);
        this.btnSet.setText(getBtnSet);
    }

    public String getBtnName() {
        return this.getBtnName;
    }

    public String getBtnSet() {
        return this.getBtnSet;
    }

    private void InfoButtonClickEvent() {
        if (this.DialogType == InfoDialogType.UNSET_TYPE) {
            Log.d(TAG, "InfoButtonClickEvent: dialog type have not been set.");
        }
        if (this.DialogType == InfoDialogType.COMMON_INFO) {
            showCommonInfoDialog();
        } else if (this.DialogType == InfoDialogType.MULTI_SELECT) {
            showMultiSelectDialog();
        }
    }

    private void showCommonInfoDialog() {
        Log.d(TAG, "prepare to show common info dialog");
    }

    private void showMultiSelectDialog() {
        Log.d(TAG, "prepare to show multi-select dialog");

        final List<Integer> choice = new ArrayList<>();
        String[] items = this.itemToShow;
        boolean[] isSelected = new boolean[this.itemNum];

        for (int i = 0; i < this.itemNum; i++)
            isSelected[i] = false;

        dialogBuilder = new AlertDialog.Builder(mContext).setTitle(R.string.alarm_clock_repeat)
                .setMultiChoiceItems(items, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            choice.add(which);
                        } else {
                            choice.remove(choice.indexOf(which));
                        }
                    }
                }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // get selected item(s) in the array
                        for (int j = 0; j < choice.size(); j++) {
                            getSelected.add(items[choice.get(j)]);
                        }

                        Log.d(TAG, getSelected + " has been selected!");
                    }
                });
        dialogBuilder.create().show();
    }

    /**
     * @param items : 用作初始化的字符串数组
     * @param num   : 选项总数
     * @param title : Dialog title
     */
    public void setMultiSelectDialogContent(String[] items, int num, String title) {
        this.itemToShow = items;
        this.itemNum = num;
        this.dialogTitle = title;
    }


    public List<String> getMultiSelectResult() {
        return this.getSelected;
    }
}
