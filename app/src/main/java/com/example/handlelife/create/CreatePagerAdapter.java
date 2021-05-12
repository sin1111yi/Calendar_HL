package com.example.handlelife.create;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.handlelife.R;

import java.util.List;

public class CreatePagerAdapter extends FragmentStateAdapter {

    List<Fragment> fragmentList;

    public CreatePagerAdapter(@NonNull FragmentManager fragmentManager,
                              @NonNull Lifecycle lifecycle,
                              List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        fragmentList=fragments;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
