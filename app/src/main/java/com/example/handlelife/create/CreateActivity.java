package com.example.handlelife.create;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.handlelife.R;
import com.example.handlelife.create.fragment.CreateAbFragment;
import com.example.handlelife.create.fragment.CreateAcFragment;
import com.example.handlelife.create.fragment.CreateErFragment;
import com.example.handlelife.create.fragment.CreateNoteFragment;
import com.example.handlelife.create.fragment.CreateSdFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class CreateActivity extends AppCompatActivity implements
        View.OnClickListener {
    private static final String TAG = "CreateActivity";

    FloatingActionButton fabReturnOverView;
    int overviewPosition;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroyed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resume: get position from OverviewActivity " + overviewPosition);
        Intent intent = getIntent();
        overviewPosition = intent.getIntExtra("OverviewPosition", 0);
        startBoostFragment(overviewPosition);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_create);

        fabReturnOverView = findViewById(R.id.fab_return_overview);
        fabReturnOverView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_return_overview) {
            Intent intent = new Intent("com.example.handlelife.OVERVIEW_PAGES");
            intent.addCategory("com.example.handlelife.OVERVIEW_ALL_ITEMS");
            startActivity(intent);
        }
    }

    //TODO: start boost fragment by this method
    public void startBoostFragment(int fragmentNum) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragmentNum == 0) {
            transaction.replace(R.id.show_fragment_zone, new CreateNoteFragment()).commit();
        } else if (fragmentNum == 1) {
            transaction.replace(R.id.show_fragment_zone, new CreateSdFragment()).commit();
        } else if (fragmentNum == 2) {
            transaction.replace(R.id.show_fragment_zone, new CreateAbFragment()).commit();
        } else if (fragmentNum == 3) {
            transaction.replace(R.id.show_fragment_zone, new CreateErFragment()).commit();
        } else if (fragmentNum == 4) {
            transaction.replace(R.id.show_fragment_zone, new CreateAcFragment()).commit();
        }
    }
}