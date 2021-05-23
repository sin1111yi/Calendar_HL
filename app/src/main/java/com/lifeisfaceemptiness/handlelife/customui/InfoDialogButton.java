package com.lifeisfaceemptiness.handlelife.customui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.lifeisfaceemptiness.handlelife.R;

import java.util.ArrayList;
import java.util.List;

public class InfoDialogButton extends RelativeLayout {

    private InfoDialogType DialogType = InfoDialogType.UNSET_TYPE;

    private TextView btnName;
    private TextView btnSet;
    private String getBtnName;
    private String getBtnSet;
    private ImageView btnImage;

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
        ENTER_STRING
    }

    private final String TAG = "InfoDialogButton";

    public InfoDialogButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.button_with_info, this);
        Button btnDialog = findViewById(R.id.btn_start_dialog);
        this.mContext = context;

        this.btnName = findViewById(R.id.btn_info_option_name);
        this.btnSet = findViewById(R.id.btn_info_option_set);
        this.btnImage = findViewById(R.id.btn_info_pic);

        btnDialog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoButtonClickEvent();
            }
        });
        Log.d(TAG, "new " + DialogType + " InfoDialogButton has been set");
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

    public void setBtnImage(int id) {
        this.btnImage.setImageDrawable(ResourcesCompat.
                getDrawable(getResources(), id, null));
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
        } else if (this.DialogType == InfoDialogType.ENTER_STRING) {
            showEnterStringDialog();
        }
    }

    private void showCommonInfoDialog() {
        Log.d(TAG, "prepare to show common info dialog");
    }

    private void showSingleSelectDialog() {
        Log.d(TAG, "prepare to show single select dialog");
        String[] items = this.itemToShow;
        final int[] choice = {-1};
        dialogBuilder = new AlertDialog.Builder(mContext).setTitle(this.dialogTitle)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        choice[0] = which;
                    }
                }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (choice[0] != -1) {
                            getSelected.append(items[choice[0]]);
                            Log.d(TAG, getSelected + " has been selected!");
                            showSingleSelectResult();
                        }
                    }
                });
        dialogBuilder.create().show();
    }

    private void showMultiSelectDialog() {
        Log.d(TAG, "prepare to show multi-select dialog");

        final List<Integer> choice = new ArrayList<>();
        String[] items = this.itemToShow;
        boolean[] isSelected = new boolean[this.itemNum];

        for (int i = 0; i < this.itemNum; i++)
            isSelected[i] = false;

        dialogBuilder = new AlertDialog.Builder(mContext).setTitle(this.dialogTitle)
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

    private void showEnterStringDialog() {
        Log.d(TAG, "prepare to show enter string dialog");
        EditText editText=new EditText(mContext);
        dialogBuilder=new AlertDialog.Builder(mContext).setTitle(this.dialogTitle).
                setView(editText).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showEnterStringResult();
            }
        });
        dialogBuilder.create().show();
    }

    /**
     * @param items   : 用作初始化的字符串数组
     * @param titleId : Dialog title
     * @brief : 设定多选对话内容
     */
    public void setMultiSelectDialogContent(String[] items, int titleId) {
        this.itemToShow = items;
        this.itemNum = items.length;
        this.dialogTitle = getResources().getString(titleId);
    }

    private void showMultiSelectResult() {
        this.getBtnSet = this.getSelected.toString();
        setBtnSet(this.getBtnSet);
    }

    /**
     * @param items:   用作初始化的字符串数组
     * @param titleId: Dialog title
     * @brief : 设定单选对话内容
     */
    public void setSingleSelectDialogContent(String[] items, int titleId) {
        this.itemToShow = items;
        this.itemNum = items.length;
        this.dialogTitle = getResources().getString(titleId);
    }

    private void showSingleSelectResult() {
        this.getBtnSet = this.getSelected.toString();
        setBtnSet(this.getBtnSet);
    }


    private void setEnterStringDialogContent(int titleId) {
        this.dialogTitle = getResources().getString(titleId);
    }

    private void showEnterStringResult() {
    }

}
