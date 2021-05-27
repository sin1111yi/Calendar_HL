package com.lifeisfaceemptiness.handlelife.create.message;

import com.lifeisfaceemptiness.handlelife.base.db.model.EventRemind;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function
 */
public class EventRemindMessage {
    private EventRemind mEventRemind;
    private int isSave;

    public EventRemindMessage(EventRemind eventRemind, int isSave) {
        mEventRemind = eventRemind;
        this.isSave = isSave;
    }

    public EventRemind getEventRemind() {
        return mEventRemind;
    }

    public void setEventRemind(EventRemind eventRemind) {
        mEventRemind = eventRemind;
    }

    public int getIsSave() {
        return isSave;
    }

    public void setIsSave(int isSave) {
        this.isSave = isSave;
    }

    @Override
    public String toString() {
        return "EventRemindMessage{" +
                "mEventRemind=" + mEventRemind +
                ", isSave=" + isSave +
                '}';
    }
}
