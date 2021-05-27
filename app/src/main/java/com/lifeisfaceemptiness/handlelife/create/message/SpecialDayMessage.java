package com.lifeisfaceemptiness.handlelife.create.message;

import com.lifeisfaceemptiness.handlelife.base.db.model.SpecialDay;

/**
 * @author JonesYang
 * @Data 2021-05-27
 * @Function
 */
public class SpecialDayMessage {

    private SpecialDay mSpecialDay;
    private int isSave;

    public SpecialDayMessage(SpecialDay specialDay, int isSave) {
        mSpecialDay = specialDay;
        this.isSave = isSave;
    }

    public SpecialDay getSpecialDay() {
        return mSpecialDay;
    }

    public void setSpecialDay(SpecialDay specialDay) {
        mSpecialDay = specialDay;
    }

    public int isSave() {
        return isSave;
    }

    public void setSave(int save) {
        isSave = save;
    }

    @Override
    public String toString() {
        return "SpecialDayMessage{" +
                "mSpecialDay=" + mSpecialDay +
                ", isSave=" + isSave +
                '}';
    }
}
