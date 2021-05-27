package com.lifeisfaceemptiness.handlelife.base.db.model;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function 日期数据实体
 */
public class SpecialDay {
    private long id;
    private String title;
    private String date;

    public SpecialDay() {
    }

    public SpecialDay(long id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SpecialDay{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
