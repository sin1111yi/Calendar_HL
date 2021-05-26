package com.lifeisfaceemptiness.handlelife.create;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.githang.statusbar.StatusBarCompat;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateSdFragment;
import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateAbFragment;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateAcFragment;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateErFragment;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateNoteFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.lifeisfaceemptiness.handlelife.note.Note_crud;

import java.util.Objects;

public class CreateActivity extends AppCompatActivity implements
        View.OnClickListener {
    private static final String TAG = "CreateActivity";

    FloatingActionButton fabReturnOverView;



    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "Destroyed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取OverviewActivity传递的intent中的position，通过position启动Fragment
        //当OverviewActivity中显示的AlarmClockFragment时，CreateActivity会启动CreateAcFragment
        int overviewPosition;
        Intent intent = getIntent();
        overviewPosition = intent.getIntExtra("OverviewPosition", 0);
        Log.d(TAG, "Resume: get position from OverviewActivity " + overviewPosition);
        startBoostFragment(overviewPosition);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage(R.string.quit_create_tip)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CreateActivity.this.finish();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        StatusBarCompat.setStatusBarColor(this,
                getResources().getColor(R.color.deep_gray, getTheme()));
        setContentView(R.layout.activity_create);

        fabReturnOverView = findViewById(R.id.fab_return_overview);
        fabReturnOverView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_return_overview) {
            Intent intent = new Intent("com.lifeisfaceemptiness.handlelife.OVERVIEW_PAGES");
            intent.addCategory("com.lifeisfaceemptiness.handlelife.OVERVIEW_ALL_ITEMS");
            startActivity(intent);
        }
    }

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