package com.example.handlelife.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.TimePicker;

// https://blog.csdn.net/An_nAl/article/details/76256219

public class HandleTimePicker extends TimePicker {

    public HandleTimePicker(Context context) {
        super(context);
    }

    public HandleTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HandleTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写如下方法即可：（应该也适用于其他控件）
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }
        return false;
    }
}

