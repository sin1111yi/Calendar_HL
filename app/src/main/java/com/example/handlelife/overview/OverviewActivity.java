package com.example.handlelife.overview;

import android.content.Intent;
import android.os.Bundle;

import com.example.handlelife.R;
import com.example.handlelife.overview.fragment.AccountBookFragment;
import com.example.handlelife.overview.fragment.AlarmClockFragment;
import com.example.handlelife.overview.fragment.CustomNoteFragment;
import com.example.handlelife.overview.fragment.EventReminderFragment;
import com.example.handlelife.overview.fragment.SpecialDateFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

public class OverviewActivity extends AppCompatActivity implements
        View.OnClickListener {

    private static final int[] TAB_TITLES = new int[]{
            R.string.item_custom_note,
            R.string.item_special_date,
            R.string.item_account_book,
            R.string.item_event_reminder,
            R.string.item_alarm_clock};
    private static final String TAG = "OverviewActivity";

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    private FloatingActionButton fabCreateCurrentItem;
    private CustomNoteFragment customNoteFragment;
    private SpecialDateFragment specialDateFragment;
    private AccountBookFragment accountBookFragment;
    private EventReminderFragment eventReminderFragment;
    private AlarmClockFragment alarmClockFragment;

    OverviewPagerAdapter overViewPagerAdapter;

    TabLayout tabLayout;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_overview);
        tabLayout = findViewById(R.id.overview_tabs_title);
        viewPager = findViewById(R.id.overview_view_pager);
        fabCreateCurrentItem = findViewById(R.id.create_current_page_item);
        fabCreateCurrentItem.setOnClickListener(this);

        customNoteFragment = new CustomNoteFragment();
        specialDateFragment = new SpecialDateFragment();
        accountBookFragment = new AccountBookFragment();
        eventReminderFragment = new EventReminderFragment();
        alarmClockFragment = new AlarmClockFragment();

        initViewPager();
    }

    private void initViewPager() {
        fragmentArrayList.add(customNoteFragment);
        fragmentArrayList.add(specialDateFragment);
        fragmentArrayList.add(accountBookFragment);
        fragmentArrayList.add(eventReminderFragment);
        fragmentArrayList.add(alarmClockFragment);

        overViewPagerAdapter = new OverviewPagerAdapter(
                getSupportFragmentManager(),
                getLifecycle(),
                fragmentArrayList);
        viewPager.setAdapter(overViewPagerAdapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(TAB_TITLES[position]));
        tabLayoutMediator.attach();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create_current_page_item) {
            Log.d(TAG, "Now position is " + viewPager.getCurrentItem());

            Intent intent = new Intent("com.example.handlelife.CREATE_PAGE");
            intent.addCategory("com.example.handlelife.CREATE_DIVERSE_ITEMS");
            // I prefer apparent everything
            Bundle bundle = new Bundle();
            bundle.putInt("OverviewPosition", viewPager.getCurrentItem());

            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}