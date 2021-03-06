package com.lifeisfaceemptiness.handlelife;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.githang.statusbar.StatusBarCompat;
import com.lifeisfaceemptiness.handlelife.base.activity.BaseActivity;
import com.lifeisfaceemptiness.handlelife.group.GroupRecyclerView;
import com.lifeisfaceemptiness.handlelife.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.TrunkBranchAnnals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnCalendarLongClickListener,
        CalendarView.OnMonthChangeListener,
        CalendarView.OnYearChangeListener,
        CalendarView.OnWeekChangeListener,
        CalendarView.OnViewChangeListener,
        CalendarView.OnCalendarInterceptListener,
        CalendarView.OnYearViewChangeListener,
        DialogInterface.OnClickListener,
        View.OnClickListener {

    TextView mTextMonthDay;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay;
    CalendarView mCalendarView;
    RelativeLayout mRelativeTool;
    private int mYear;
    CalendarLayout mCalendarLayout;

    FloatingActionButton fabCreateItem;
    FloatingActionButton fabGithub;
    FloatingActionButton fabStatData;

    public static final String TAG = "MainActivity";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //???????????????
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.white, getTheme()));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        setStatusBarDarkMode();
        mTextMonthDay = (TextView) findViewById(R.id.tv_month_day);
        mTextYear = (TextView) findViewById(R.id.tv_year);
        mTextLunar = (TextView) findViewById(R.id.tv_lunar);
        mRelativeTool = (RelativeLayout) findViewById(R.id.rl_tool);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mTextCurrentDay = (TextView) findViewById(R.id.tv_current_day);
        fabCreateItem = (FloatingActionButton) findViewById(R.id.fab_create);
        fabGithub = (FloatingActionButton) findViewById(R.id.fab_github);

        mTextMonthDay.setOnClickListener(v -> {
            if (!mCalendarLayout.isExpand()) {
                mCalendarView.showYearSelectLayout(mYear);
                return;
            }
            mCalendarView.showYearSelectLayout(mYear);
            mTextLunar.setVisibility(View.GONE);
            mTextYear.setVisibility(View.GONE);
            mTextMonthDay.setText(String.valueOf(mYear));
        });
        findViewById(R.id.fl_current).setOnClickListener(v -> mCalendarView.scrollToCurrent());
        mCalendarLayout = (CalendarLayout) findViewById(R.id.calendarLayout);
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "???" + mCalendarView.getCurDay() + "???");
        mTextLunar.setText("??????");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));

        fabCreateItem.setOnClickListener(this);
        fabGithub.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        Map<String, Calendar> map = new HashMap<>();

        //TODO: Get data from database

        mCalendarView.setSchemeDate(map);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case 0:
                mCalendarView.setWeekStarWithSun();
                break;
            case 1:
                mCalendarView.setWeekStarWithMon();
                break;
            case 2:
                mCalendarView.setWeekStarWithSat();
                break;
            case 3:
                if (mCalendarView.isSingleSelectMode()) {
                    mCalendarView.setSelectDefaultMode();
                } else {
                    mCalendarView.setSelectSingleMode();
                }
                break;
            case 4:
                mCalendarView.setWeekBar(EnglishWeekBar.class);
                break;
            case 5:
                mCalendarView.setAllMode();
                break;
            case 6:
                mCalendarView.setOnlyCurrentMode();
                break;
            case 7:
                mCalendarView.setFixMode();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_create) {
            // use implicit intent to jump to create activity
            Intent intent = new Intent("com.lifeisfaceemptiness.handlelife.OVERVIEW_PAGES");
            intent.addCategory("com.lifeisfaceemptiness.handlelife.OVERVIEW_ALL_ITEMS");
            startActivity(intent);
        } else if (v.getId() == R.id.fab_github) {
            // show github
            Uri uri = Uri.parse("https://github.com/sin1111yi/Calendar_HL");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        } else {
            Log.d(TAG, "there's no event for this click");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    /**
     * @brief ???????????????????????????????????????????????????????????????
     */
    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//???????????????????????????????????????????????????
        calendar.setScheme(text);
        return calendar;
    }


    @Override
    public void onCalendarOutOfRange(Calendar calendar) {
        Toast.makeText(this, String.format("%s : OutOfRange", calendar), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        //Log.e("onDateSelected", "  -- " + calendar.getYear() + "  --  " + calendar.getMonth() + "  -- " + calendar.getDay());
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "???" + calendar.getDay() + "???");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
        if (isClick) {
            //TODO: ?????????????????????????????????????????????????????????
            Toast.makeText(this, getCalendarText(calendar), Toast.LENGTH_SHORT).show();
        }
//        Log.e("lunar "," --  " + calendar.getLunarCalendar().toString() + "\n" +
//        "  --  " + calendar.getLunarCalendar().getYear());
        Log.e("onDateSelected", "  -- " + calendar.getYear() +
                "  --  " + calendar.getMonth() +
                "  -- " + calendar.getDay() +
                "  --  " + isClick + "  --   " + calendar.getScheme());
        Log.e("onDateSelected", "  " + mCalendarView.getSelectedCalendar().getScheme() +
                "  --  " + mCalendarView.getSelectedCalendar().isCurrentDay());
        Log.e("???????????? ??? ", " -- " + TrunkBranchAnnals.getTrunkBranchYear(calendar.getLunarCalendar().getYear()));
    }

    @Override
    public void onCalendarLongClickOutOfRange(Calendar calendar) {
        Toast.makeText(this, String.format("%s : LongClickOutOfRange", calendar), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCalendarLongClick(Calendar calendar) {
        Toast.makeText(this, "?????????????????????\n" + getCalendarText(calendar), Toast.LENGTH_LONG).show();
    }

    private static String getCalendarText(Calendar calendar) {
        return String.format(" ??????%s \n ??????%s \n ???????????????%s \n ???????????????%s \n ?????????%s \n ???????????????%s",
                calendar.getMonth() + "???" + calendar.getDay() + "???",
                calendar.getLunarCalendar().getMonth() + "???" + calendar.getLunarCalendar().getDay() + "???",
                TextUtils.isEmpty(calendar.getGregorianFestival()) ? "???" : calendar.getGregorianFestival(),
                TextUtils.isEmpty(calendar.getTraditionFestival()) ? "???" : calendar.getTraditionFestival(),
                TextUtils.isEmpty(calendar.getSolarTerm()) ? "???" : calendar.getSolarTerm(),
                calendar.getLeapMonth() == 0 ? "???" : String.format("???%s???", calendar.getLeapMonth()));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onMonthChange(int year, int month) {
        Log.e("onMonthChange", "  -- " + year + "  --  " + month);
        Calendar calendar = mCalendarView.getSelectedCalendar();
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "???" + calendar.getDay() + "???");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
    }

    @Override
    public void onViewChange(boolean isMonthView) {
        Log.e("onViewChange", "  ---  " + (isMonthView ? "?????????" : "?????????"));
    }


    @Override
    public void onWeekChange(List<Calendar> weekCalendars) {
        for (Calendar calendar : weekCalendars) {
            Log.e("onWeekChange", calendar.toString());
        }
    }

    @Override
    public void onYearViewChange(boolean isClose) {
        Log.e("onYearViewChange", "????????? -- " + (isClose ? "??????" : "??????"));
    }

    /**
     * ????????????????????????????????????????????????????????????????????????
     *
     * @param calendar calendar
     * @return ??????????????????????????????????????????MonthView???WeekView????????????API?????????
     */
    @Override
    public boolean onCalendarIntercept(Calendar calendar) {
        Log.e("onCalendarIntercept", calendar.toString());
        int day = calendar.getDay();
        return day == 1 || day == 3 || day == 6 || day == 11 || day == 12 || day == 15 || day == 20 || day == 26;
    }

    @Override
    public void onCalendarInterceptClick(Calendar calendar, boolean isClick) {
        Toast.makeText(this, calendar.toString() + "??????????????????", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
        Log.e("onYearChange", " ???????????? " + year);
    }

}


