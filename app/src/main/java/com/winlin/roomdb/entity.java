package com.winlin.roomdb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 更改：将变量riqi更改为date，修改了对应的函数
 *
 * 需要：对照sinyi.uselessitem.UselessItem中成员完善数据库必要的有：
 * itemId, itemDataTag, itemTitle, itemContent, itemCreateTime, itemUpdateTime
 * 请在数据库内加入类型标志，实例如下所示：
 *    //UselessItem内部的枚举类，用于标识项目类型
 *     public enum itemDataType {
 *         isUnknownType,    // 0 未知
 *         isCustomNode,     // 1 笔记
 *         isSpecialDate,    // 2 特别日期
 *         isAccountBook,    // 3 账本
 *         isEventReminder,  // 4 事件提醒
 *         isAlarmClock;     // 5 闹钟
 *     }
 * 方便设定数据类型，可以参照如下过程：
 *     private itemDataType itemDataTag;
 *     public void setItemDataTag(itemDataType dataTag) {
 *         this.itemDataTag = dataTag;
 *     }
 *
 * 建议：进一步规范变量命名，全部使用英文单词，调整类命名
 * */

@Entity
public class entity {
    //主键
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    //变量：类别、标题、时间、内容等。
    private String kindle;
    private String title;
    private int date;
    private String content;

    public entity(Integer id, String kindle, String title, int date, String content) {
        this.id = id;
        this.kindle = kindle;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKindle() {
        return this.kindle;
    }

    public void setKindle(String kindle) {
        this.kindle = kindle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "entity{" +
                "id=" + id +
                ", kindle='" + kindle + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}





