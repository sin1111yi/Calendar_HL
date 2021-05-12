package com.sinyi.uselessitem.fragment;

import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {

    private long itemId;
    private char itemDataTag;
    private String itemTitle;
    private String itemContent;
    private String itemCreateTime;
    private String itemUpdateTime;

    private enum itemDataType{
        isNode,           //笔记
        isSpecialDate,    //特别日期
        isAccountBook,    //账本
        isEventReminder,  //事件提醒
        isAlarmClock;     //闹钟
    }

    public ItemFragment() {

    }

    void init(char DataTag, String Title, String Content, String CreateTime) {
        this.itemDataTag = DataTag;
        this.itemTitle = Title;
        this.itemContent = Content;
        this.itemCreateTime = CreateTime;
        this.itemUpdateTime = CreateTime;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long id) {
        this.itemId = id;
    }

    public char getItemDataTag() {
        return itemDataTag;
    }

    public void setItemDataTag(char dataTag) {
        this.itemDataTag = dataTag;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String content) {
        this.itemContent = content;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String title) {
        this.itemTitle = title;
    }

    public String getItemCreateTime() {
        return itemCreateTime;
    }

    public void setItemCreateTime(String createTime) {
        this.itemCreateTime = createTime;
    }

    public String getItemUpdateTimeTime() {
        return itemUpdateTime;
    }

    public void setItemUpdateTime(String updateTime) {
        this.itemUpdateTime = updateTime;
    }

    private float contentStrToFloatAmount() {
        return Float.parseFloat(this.itemContent);
    }

}
