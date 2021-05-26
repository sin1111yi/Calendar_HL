package com.lifeisfaceemptiness.handlelife.create;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.githang.statusbar.StatusBarCompat;
import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateAbFragment;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateAcFragment;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateErFragment;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateNoteFragment;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateSdFragment;
import com.lifeisfaceemptiness.handlelife.note.NoteMessage;

import org.greenrobot.eventbus.EventBus;

public class CreateActivity extends AppCompatActivity implements
        View.OnClickListener {

    private static final String TAG = "CreateActivity";

    FloatingActionButton fabReturnOverView;

    // 初始化 Fragment
    final CreateNoteFragment mCreateNoteFragment = new CreateNoteFragment();
    final CreateSdFragment mCreateSdFragment = new CreateSdFragment();
    final CreateAbFragment mCreateAbFragment = new CreateAbFragment();
    final CreateErFragment mCreateErFragment = new CreateErFragment();
    final CreateAcFragment mCreateAcFragment = new CreateAcFragment();

    private String noteContent;


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

    /**
     * 当前 Activity 的返回事件
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage(R.string.quit_create_tip)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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

    /**
     * @param v 悬浮图标的点击事件
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_return_overview) {
            Intent intent = new Intent("com.lifeisfaceemptiness.handlelife.OVERVIEW_PAGES");
            intent.addCategory("com.lifeisfaceemptiness.handlelife.OVERVIEW_ALL_ITEMS");
            newsDelivery();
            startActivity(intent);
        }
    }


    /**
     * TODO ：这个函数用来接受来自其他 Fragment 的数据
     * 用于做数据传递的，接受来自对应Fragment的值
     */
    private void newsDelivery() {
        // 接受来自CreateNoteFragment的数据
        mCreateNoteFragment.sendNoteData(new CreateNoteFragment.ISendNoteDataListener() {
            @Override
            public void postNoteData(String s) {
                noteContent = s;
            }
        });
        // EventBus 进行数据传递
        EventBus.getDefault().post(new NoteMessage(noteContent, 1));
    }


    /**
     * 根据 position 启动对应的Fragment
     *
     * @param fragmentNum
     */
    public void startBoostFragment(int fragmentNum) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (fragmentNum) {
            case 0:
                transaction.replace(R.id.show_fragment_zone, mCreateNoteFragment).commit();
                break;
            case 1:
                transaction.replace(R.id.show_fragment_zone, mCreateSdFragment).commit();
                break;
            case 2:
                transaction.replace(R.id.show_fragment_zone, mCreateAbFragment).commit();
                break;
            case 3:
                transaction.replace(R.id.show_fragment_zone, mCreateErFragment).commit();
                break;
            case 4:
                transaction.replace(R.id.show_fragment_zone, mCreateAcFragment).commit();
                break;
            default:
                break;
        }
    }

}