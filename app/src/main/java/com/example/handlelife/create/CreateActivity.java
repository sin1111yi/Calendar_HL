package com.example.handlelife.create;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.handlelife.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class CreateActivity extends AppCompatActivity implements
        View.OnClickListener{

    //TODO: 从OverViewActivity获取OverViewActivity中按键触发时ViewPager2的position，实现位置当前的为OverViewActivity的第101行


    FloatingActionButton fabReturnOverView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        fabReturnOverView=findViewById(R.id.fab_return_overview);

        fabReturnOverView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fab_return_overview)
        {
            Intent intent = new Intent("com.example.handlelife.OVERVIEW_PAGES");
            intent.addCategory("com.example.handlelife.OVERVIEW_ALL_ITEMS");
            startActivity(intent);
        }
    }
}