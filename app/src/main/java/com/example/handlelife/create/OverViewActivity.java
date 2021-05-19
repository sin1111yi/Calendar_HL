package com.example.handlelife.create;

import android.os.Bundle;

import com.example.handlelife.R;
import com.example.handlelife.create.fragment.AccountBookFragment;
import com.example.handlelife.create.fragment.AlarmClockFragment;
import com.example.handlelife.create.fragment.CreateAcFragment;
import com.example.handlelife.create.fragment.CustomNoteFragment;
import com.example.handlelife.create.fragment.EventReminderFragment;
import com.example.handlelife.create.fragment.SpecialDateFragment;
import com.example.handlelife.customui.FragmentGetPosition;
import com.example.handlelife.customui.FragmentTransfer;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

public class OverViewActivity extends AppCompatActivity implements
        View.OnClickListener,
        FragmentTransfer{

    private static final int[] TAB_TITLES = new int[]{
            R.string.item_custom_note,
            R.string.item_special_date,
            R.string.item_account_book,
            R.string.item_event_reminder,
            R.string.item_alarm_clock};

    private CustomNoteFragment customNoteFragment;
    private SpecialDateFragment specialDateFragment;
    private AccountBookFragment accountBookFragment;
    private EventReminderFragment eventReminderFragment;
    private AlarmClockFragment alarmClockFragment;

    private CreateAcFragment createAcFragment;

    OverViewPagerAdapter createPagerAdapter;

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_over_view);
        tabLayout = findViewById(R.id.over_view_tabs_title);
        viewPager = findViewById(R.id.over_view_view_pager);

        customNoteFragment = new CustomNoteFragment();
        specialDateFragment = new SpecialDateFragment();
        accountBookFragment = new AccountBookFragment();
        eventReminderFragment = new EventReminderFragment();
        alarmClockFragment = new AlarmClockFragment();

        createAcFragment = new CreateAcFragment();

        createAcFragment.setFragmentTransfer(this);

        initViewPager();
    }

    private void initViewPager() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(customNoteFragment);
        fragmentArrayList.add(specialDateFragment);
        fragmentArrayList.add(accountBookFragment);
        fragmentArrayList.add(eventReminderFragment);
        fragmentArrayList.add(createAcFragment);

        createPagerAdapter = new OverViewPagerAdapter(
                getSupportFragmentManager(),
                getLifecycle(),
                fragmentArrayList);
        viewPager.setAdapter(createPagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TAB_TITLES[position]));
        tabLayoutMediator.attach();

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void transferFragment(int position) {

    }

}