package com.example.handlelife.create.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.handlelife.R;
import com.example.handlelife.customui.FragmentTransfer;
import com.example.handlelife.customui.InfoDialogButton;
import com.example.handlelife.customui.InfoDialogButton.InfoDialogType;
import com.sinyi.uselessitem.base.UselessItem;
import com.sinyi.uselessitem.base.UselessItem.itemDataType;
import com.suke.widget.SwitchButton;

import org.jetbrains.annotations.NotNull;


public class AlarmClockFragment extends Fragment implements
        View.OnClickListener,
        TimePicker.OnTimeChangedListener,
        SwitchButton.OnCheckedChangeListener {

    View rootView;
    private static final String TAG = "CreateAlarmClock";

    private TimePicker timePicker;
    private Context mContext;

    private String AlarmClockTime;

    private final UselessItem uselessItem = new UselessItem();
    private final itemDataType alarmClock = itemDataType.isAlarmClock;

    private InfoDialogButton infoButtonTitle;
    private InfoDialogButton infoButtonRepeat;
    private InfoDialogButton infoButtonSound;
    private InfoDialogButton infoButtonRingDuration;
    private InfoDialogButton infoButtonSnoozeDuration;
    private SwitchButton switchButtonHoliday;
    private NestedScrollView nestedScrollViewCreate;

    private FragmentTransfer fragmentTransfer;

    public AlarmClockFragment() {
        // Required empty public constructor
    }

    public static AlarmClockFragment newInstance() {
        AlarmClockFragment fragment = new AlarmClockFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_alarm_clock, container, false);
        }

        timePicker = rootView.findViewById(R.id.alarm_clock_time_picker);
        timePicker.setOnTimeChangedListener(this);

        switchButtonHoliday = rootView.findViewById(R.id.alarm_clock_holiday);
        switchButtonHoliday.setOnClickListener(this);
        switchButtonHoliday.setOnCheckedChangeListener(this);

        nestedScrollViewCreate = rootView.findViewById(R.id.alarm_clock_build);

        initInfoButtons();

        return rootView;
    }

    public void initInfoButtons() {
        infoButtonTitle = rootView.findViewById(R.id.alarm_clock_set_title);
        infoButtonRepeat = rootView.findViewById(R.id.alarm_clock_repeat);
        infoButtonSound = rootView.findViewById(R.id.alarm_clock_sound);
        infoButtonRingDuration = rootView.findViewById(R.id.alarm_clock_ring_duration);
        infoButtonSnoozeDuration = rootView.findViewById(R.id.alarm_clock_snooze_duration);

        infoButtonTitle.setBtnName(R.string.alarm_clock_default_title);
        infoButtonRepeat.setBtnName(R.string.alarm_clock_repeat);
        infoButtonRepeat.setDialogType(InfoDialogType.MULTI_SELECT);
        infoButtonSound.setBtnName(R.string.alarm_clock_sound);
        infoButtonRingDuration.setBtnName(R.string.alarm_clock_ring_duration);
        infoButtonRingDuration.setDialogType(InfoDialogType.SINGLE_SELECT);
        infoButtonSnoozeDuration.setBtnName(R.string.alarm_clock_snooze_duration);

        infoButtonRepeat.setMultiSelectDialogContent(getResources().
                        getStringArray(R.array.complete_week_string_array),
                R.string.alarm_clock_repeat);
        infoButtonRingDuration.setMultiSelectDialogContent(getResources().
                        getStringArray(R.array.alarm_clock_ring_duration_value_array),
                R.string.alarm_clock_ring_duration);
    }

    @Override
    public void onClick(View v) {

    }

    private void setFragmentTransfer(FragmentTransfer FT) {
        this.fragmentTransfer = FT;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        AlarmClockTime = hourOfDay + "h" + minute + "m";
        Log.d(TAG, "Selected time changed to " + AlarmClockTime);
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        Log.d(TAG, "Ignore holiday has been set to " + isChecked);
    }

    public void SaveAllData() {
        uselessItem.setItemContent(AlarmClockTime);
        uselessItem.setItemDataTag(alarmClock);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "AlarmClock has been paused");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SaveAllData();
        Log.d(TAG, "AlarmClock has been destroyed");
    }
}