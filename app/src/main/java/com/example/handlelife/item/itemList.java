package com.example.handlelife.item;

import android.app.Activity;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import androidx.fragment.app.FragmentActivity;

import com.example.handlelife.R;

import java.util.LinkedList;

public class itemList extends FragmentActivity {

    private LinearLayout llContentView;
    private EditText editContent1;
    private LinkedList<Button> buttonListAdd;
    private int buttonIdIndex = 999;
    private LinkedList<Button> buttonListDel;

    private int editContentHeight = 0;
    private float fDimRatio = 1.0f;

    private void initCtrl() {
        llContentView = (LinearLayout) this.findViewById(R.id.content_view);
        editContent1 = (EditText) this.findViewById(R.id.edit_content1);
        buttonListAdd = new LinkedList<Button>();
        buttonListDel = new LinkedList<Button>();

        Button buttonAdd1 = (Button) this.findViewById(R.id.itemlist_Add1);
        buttonAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取尺寸变化比例
                editContentHeight = editContent1.getHeight();
                fDimRatio = editContentHeight / 80;

                addContent(v);// 第一组隐藏了“-”按钮，所以为null
            }
        });

        buttonListAdd.add(buttonAdd1);
        buttonListDel.add(null);
    }

    /**
     * 添加一组新控件
     *
     * @param v 事件触发控件，就是触发添加事件对应的“+”按钮
     */
    private void addContent(View v) {
        if (v == null) {
            return;
        }
        // 判断第几个“+”按钮触发了事件
        int itemIndex = -1;
        for (int loop_ctrl = 0; loop_ctrl < buttonListAdd.size(); loop_ctrl++) {
            if (buttonListAdd.get(loop_ctrl) == v) {
                itemIndex = loop_ctrl;
                break;
            }
        }
        if (itemIndex >= 0) {
            // 控件实际添加位置为当前触发位置点下一位
            itemIndex += 1;
            // 开始添加控件
            // 1.创建外围LinearLayout控件
            LinearLayout layout =new LinearLayout(itemList.this);
            LinearLayout.LayoutParams LLayoutParams =new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 设置margin
            LLayoutParams.setMargins(0,(int)(fDimRatio*5),0,0);
            layout.setLayoutParams(LLayoutParams);
            // 设置属性
            layout.setBackgroundColor(Color.argb(255,162,205,90));
            layout.setPadding((int) (fDimRatio * 5), (int) (fDimRatio * 5),
                    (int) (fDimRatio * 5), (int) (fDimRatio * 5));
            layout.setOrientation(LinearLayout.VERTICAL);

            // 2.创建内部EditText控件
            EditText etContent = new EditText(itemList.this);
            LinearLayout.LayoutParams etParam = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, editContentHeight);
            etContent.setLayoutParams(etParam);
            // 设置属性
            etContent.setBackgroundColor(Color.argb(255, 255, 255, 255));   // #FFFFFFFF
            etContent.setGravity(Gravity.LEFT);
            etContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            etContent.setPadding((int) (fDimRatio * 5), 0, 0, 0);
            etContent.setTextSize(16);
            // 将EditText放到LinearLayout里
            layout.addView(etContent);

            // 3.创建“+”和“-”按钮外围控件RelativeLayout
            RelativeLayout rlBtn = new RelativeLayout(itemList.this);
            RelativeLayout.LayoutParams rlParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // rlParam.setMargins(0, (int) (fDimRatio * 5), 0, 0);
            rlBtn.setPadding(0, (int) (fDimRatio * 5), 0, 0);
            rlBtn.setLayoutParams(rlParam);

            // 4.创建“+”按钮
            Button buttonAdd = new Button(itemList.this);
            RelativeLayout.LayoutParams btnAddParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 靠右放置
            btnAddParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            buttonAdd.setLayoutParams(btnAddParam);
            // 设置属性
            buttonAdd.setId(buttonIdIndex);
            // 设置点击操作
            buttonAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    addContent(v);
                }
            });
            // 将“+”按钮放到RelativeLayout里
            rlBtn.addView(buttonAdd);
            buttonListAdd.add(itemIndex, buttonAdd);

            // 5.创建“-”按钮
            Button btnDelete = new Button(itemList.this);
            RelativeLayout.LayoutParams btnDeleteAddParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            btnDeleteAddParam.setMargins(0, 0, (int) (fDimRatio * 5), 0);
            // “-”按钮放在“+”按钮左侧
            btnDeleteAddParam.addRule(RelativeLayout.LEFT_OF, buttonIdIndex);
            btnDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    deleteContent(v);
                }
            });
            // 将“-”按钮放到RelativeLayout里
            rlBtn.addView(btnDelete, btnDeleteAddParam);
            buttonListDel.add(itemIndex, btnDelete);

            // 6.将RelativeLayout放到LinearLayout里
            layout.addView(rlBtn);

            // 7.将layout同它内部的所有控件加到最外围的llContentView容器里
            llContentView.addView(layout, itemIndex);

            buttonIdIndex++;
        }
    }
    /**
     * 删除一组控件
     * @param v 事件触发控件，其实就是触发删除事件对应的“-”按钮
     */
    private void deleteContent(View v) {
        if (v == null) {
            return;
        }

        // 判断第几个“-”按钮触发了事件
        int iIndex = -1;
        for (int i = 0; i < buttonListDel.size(); i++) {
            if (buttonListDel.get(i) == v) {
                iIndex = i;
                break;
            }
        }
        if (iIndex >= 0) {
            buttonListAdd.remove(iIndex);
            buttonListDel.remove(iIndex);

            // 从外围llContentView容器里删除第iIndex控件
            llContentView.removeViewAt(iIndex);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }  */

}
