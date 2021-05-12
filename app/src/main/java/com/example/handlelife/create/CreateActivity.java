package com.example.handlelife.create;

import android.content.Intent;
import android.os.Bundle;

import com.example.handlelife.R;
import com.example.handlelife.create.fragment.AccountBookFragment;
import com.example.handlelife.create.fragment.AlarmClockFragment;
import com.example.handlelife.create.fragment.CustomNoteFragment;
import com.example.handlelife.create.fragment.EventReminderFragment;
import com.example.handlelife.create.fragment.SpecialDateFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

public class CreateActivity extends AppCompatActivity implements
        View.OnClickListener {

    private static final int[] TAB_TITLES = new int[]{
            R.string.item_custom_note,
            R.string.item_special_date,
            R.string.item_account_book,
            R.string.item_event_reminder,
            R.string.item_alarm_clock};

    private final CustomNoteFragment customNoteFragment = new CustomNoteFragment();
    private final SpecialDateFragment specialDateFragment = new SpecialDateFragment();
    private final AccountBookFragment accountBookFragment = new AccountBookFragment();
    private final EventReminderFragment eventReminderFragment = new EventReminderFragment();
    private final AlarmClockFragment alarmClockFragment = new AlarmClockFragment();

    FloatingActionButton fab;
    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_create);
        fab = findViewById(R.id.create_fab);
        tabLayout = findViewById(R.id.create_tabs_title);
        viewPager = findViewById(R.id.create_view_pager);
        initViewPager();
    }

    private void initViewPager() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(customNoteFragment);
        fragmentArrayList.add(specialDateFragment);
        fragmentArrayList.add(accountBookFragment);
        fragmentArrayList.add(eventReminderFragment);
        fragmentArrayList.add(alarmClockFragment);

        CreatePagerAdapter createPagerAdapter = new CreatePagerAdapter(
                getSupportFragmentManager(),
                getLifecycle(),
                fragmentArrayList);
        viewPager.setAdapter(createPagerAdapter);

        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TAB_TITLES[position]));
        tabLayoutMediator.attach();

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create_fab) {
            //TODO: attach to database insert
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("com.example.handlelife.BEGINNING_ACTIVITY");
            startActivity(intent);
        }
    }
}