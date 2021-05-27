package com.lifeisfaceemptiness.handlelife.base.db.model;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function 事件提醒的数据实体
 */
public class EventRemind {
    private long id;

    private String name;

    private String time;

    public EventRemind() {
    }

    public EventRemind(long id, String name, String time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "EventRemind{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
