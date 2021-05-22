package com.lifeisfaceemptiness.handlelife.customui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lifeisfaceemptiness.handlelife.R;

import java.util.ArrayList;
import java.util.List;

public class InfoDialogButton extends RelativeLayout{

    private InfoDialogType DialogType = InfoDialogType.UNSET_TYPE;

    private TextView btnName;
    private TextView btnSet;
    private String getBtnName;
    private String getBtnSet;

    private Context mContext;

    private StringBuilder getSelected = new StringBuilder();
    private String[] itemToShow;
    private int itemNum;
    private String dialogTitle;

    private AlertDialog.Builder dialogBuilder;

    public enum InfoDialogType {
        UNSET_TYPE,
        COMMON_INFO,
        SINGLE_SELECT,
        MULTI_SELECT,
    }

    private final String TAG = "InfoDialogButton";

    public InfoDialogButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.button_with_info, this);
        Button btnDialog = findViewById(R.id.btn_start_dialog);
        this.mContext = context;

        this.btnName = findViewById(R.id.btn_info_option_name);
        this.btnSet = findViewById(R.id.btn_info_option_set);

        btnDialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoButtonClickEvent();
            }
        });
        Log.d(TAG, "new "+DialogType+" InfoDialogButton has been set");
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

    public void setBtnSet(String str) {
        this.btnSet.setText(str);
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
        } else if (this.DialogType == InfoDialogType.SINGLE_SELECT) {
            showSingleSelectDialog();
        } else if (this.DialogType == InfoDialogType.MULTI_SELECT) {
            showMultiSelectDialog();
        }
    }

    private void showCommonInfoDialog() {
        Log.d(TAG, "prepare to show common info dialog");
    }

    private void showSingleSelectDialog()
    {
        Log.d(TAG, "prepare to show single select dialog");
        //TODO: complete this method
    }

    private void showMultiSelectDialog() {
        Log.d(TAG, "prepare to show multi-select dialog");

        final List<Integer> choice = new ArrayList<>();
        String[] items = this.itemToShow;
        boolean[] isSelected = new boolean[this.itemNum];

        for (int i = 0; i < this.itemNum; i++)
            isSelected[i] = false;

        dialogBuilder = new AlertDialog.Builder(mContext).setTitle(dialogTitle)
                .setMultiChoiceItems(items, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            choice.add(which);
                        } else {
                            choice.remove((Integer) which);
                        }
                    }
                }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // get selected item(s) in the array
                        getSelected = new StringBuilder();
                        for (int j = 0; j < choice.size(); j++) {
                            getSelected.append(items[choice.get(j)]);
                            if (j < choice.size() - 1)
                                getSelected.append(getResources().getString(R.string.comma));
                        }

                        Log.d(TAG, getSelected + " has been selected!");
                        showMultiSelectResult();
                    }
                });
        dialogBuilder.create().show();
    }

    /**
     * @brief : 设定多选对话内容
     * @param items : 用作初始化的字符串数组
     * @param titleId : Dialog title
     */
    public void setMultiSelectDialogContent(String[] items, int titleId) {
        this.itemToShow = items;
        this.itemNum=items.length;
        this.dialogTitle = getResources().getString(titleId);
    }

    public void showMultiSelectResult() {
        this.getBtnSet = this.getSelected.toString();
        setBtnSet(this.getBtnSet);
    }

    /**
     * @brief : 设定单选对话内容
     * @param items: 用作初始化的字符串数组
     * @param titleId: Dialog title
     */
    public void setSingleSelectDialogContent(String[] items,int titleId)
    {
        this.itemToShow=items;
        this.itemNum=items.length;
        this.dialogTitle=getResources().getString(titleId);
    }

    public void showSingleSelectResult()
    {
        this.getBtnSet = this.getSelected.toString();
        setBtnSet(this.getBtnSet);
    }

}
