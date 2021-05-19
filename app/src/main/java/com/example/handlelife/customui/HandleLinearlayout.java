package com.example.handlelife.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.LinearLayout;

// https://blog.csdn.net/An_nAl/article/details/76256219

public class HandleLinearlayout extends LinearLayout {

    public HandleLinearlayout(Context context) {
        super(context);
    }

    public HandleLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HandleLinearlayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //请求其Parents不对Touch进行拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            ViewParent p = getParent();
            if (p != null)
                p.requestDisallowInterceptTouchEvent(true);
        }

        return false;
    }

    //给LinearLayout加入Touch事件，使得所有Layout区域的Touch操作均不受拦截
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

}
