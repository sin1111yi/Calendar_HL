package com.sinyi.uselessitem.base;

import android.util.Log;

import java.util.Calendar;

public class UselessItem {

    private long itemId;
    private itemDataType itemDataTag;
    private String itemTitle;
    private String itemContent;
    private String itemCreateTime;
    private String itemUpdateTime;
    private String itemSimpleContent;
    private float itemAccount;

    //UselessItem内部的枚举类，用于标识项目类型
    public enum itemDataType {
        isUnknownType,    // 0 未知
        isCustomNode,     // 1 笔记
        isSpecialDate,    // 2 特别日期
        isAccountBook,    // 3 账本
        isEventReminder,  // 4 事件提醒
        isAlarmClock;     // 5 闹钟
    }

    public UselessItem(itemDataType DataTag, String Title, String Content) {
        this.itemDataTag = DataTag;
        this.itemTitle = Title;
        this.itemContent = Content;
        this.itemCreateTime = this.getSystemTime();
        this.itemUpdateTime = this.itemCreateTime;
    }

    public UselessItem() {

    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long id) {
        this.itemId = id;
    }

    public void setItemDataTag(itemDataType dataTag) {
        this.itemDataTag = dataTag;
        if (dataTag == itemDataType.isAccountBook) {
            // if dataTag is AccountBook
            this.contentStrToFloatAmount();
        }
    }

    public int getItemDataTagInteger() {
        if (this.itemDataTag == itemDataType.isCustomNode) {
            return 1;
        } else if (this.itemDataTag == itemDataType.isSpecialDate) {
            return 2;
        } else if (this.itemDataTag == itemDataType.isAccountBook) {
            return 3;
        } else if (this.itemDataTag == itemDataType.isEventReminder) {
            return 4;
        } else if (this.itemDataTag == itemDataType.isAlarmClock) {
            return 5;
        } else {
            return 0;
        }
    }

    public itemDataType getItemDataTag() {
        return this.itemDataTag;
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

    public String getItemUpdateTime() {
        return itemUpdateTime;
    }

    public void setItemUpdateTime(String updateTime) {
        this.itemUpdateTime = updateTime;
    }

    private void setItemSimpleContent() {
        this.itemSimpleContent = this.itemContent.substring(0, 9);
    }

    public String getItemSimpleContent() {
        if (this.itemSimpleContent == null)
            setItemSimpleContent();

        return this.itemSimpleContent;
    }

    public float getItemAccount() {
        return this.itemAccount;
    }

    private void contentStrToFloatAmount() {
        this.itemAccount = Float.parseFloat(this.itemContent);
    }

    public String getSystemTime() {
        Calendar calendar = Calendar.getInstance();
        String systemTime = calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"// count from 0
                + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
                + calendar.get(Calendar.HOUR_OF_DAY) + "时"
                + calendar.get(Calendar.MINUTE) + "分";
        Log.e("msg", "Called on " + systemTime);
        return systemTime;
    }

}
