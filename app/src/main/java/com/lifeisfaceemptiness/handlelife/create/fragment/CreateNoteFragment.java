package com.lifeisfaceemptiness.handlelife.create.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.IntentSender;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifeisfaceemptiness.handlelife.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;



import com.lifeisfaceemptiness.handlelife.note.NoteDatabase;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNoteFragment extends Fragment implements AdapterView.OnItemClickListener{

    EditText et;
    // private String content;
    // private String time;
    private Context mContext;
    View rootView;

    private Toolbar myToolbar;
    private String old_content = "";
    private String old_time = "";
    private int old_Tag = 1;
    private long id = 0;
    private int openMode = 0;
    private int tag = 1;
    public Intent intent = new Intent(); // message to be sent
    private boolean tagChange = false;
    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;



    @Override
    public void onDestroy() {
        super.onDestroy();
//        Intent intent = new Intent();
//
//        intent.putExtra("mode", 0); // new one note;
//        intent.putExtra("content", et.getText().toString());
//        intent.putExtra("time", dateToStr());
//        intent.putExtra("tag", tag);

        if(et.getText().toString().length() == 0){
//                intent.putExtra("mode", -1); //nothing new happens.
            }
            else{
//                intent.putExtra("mode", 0); // new one note;
//                intent.putExtra("content", et.getText().toString());
//                intent.putExtra("time", dateToStr());
//                intent.putExtra("tag", tag);

                EventBus.getDefault().post(et.getText().toString());
            }


//
//        Log.d("TAG", et.getText().toString());
//        Log.d("TAG", dateToStr());

        //startActivityForResult(RESULT_OK, intent);
//            setResult(RESULT_OK, intent);
        //finish();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_note_show, container, false);
        }
        et = rootView.findViewById(R.id.et1);

        Intent getIntent = getActivity().getIntent();
        int openMode = getIntent.getIntExtra("mode", 0);

        return rootView;

    }

//    public boolean onKeyDown(int keyCode, KeyEvent event){
//        if (keyCode == KeyEvent.KEYCODE_HOME){
//            return true;
//        }
//        else if (keyCode == KeyEvent.KEYCODE_BACK){
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }

//
//    public void autoSetMessage(){
//        if(openMode == 4){
//            if(et.getText().toString().length() == 0){
//                intent.putExtra("mode", -1); //nothing new happens.
//            }
//            else{
//                intent.putExtra("mode", 0); // new one note;
//                intent.putExtra("content", et.getText().toString());
//                intent.putExtra("time", dateToStr());
//                intent.putExtra("tag", tag);
//            }
//        }
//        else {
//            if (et.getText().toString().equals(old_content) && !tagChange)
//                intent.putExtra("mode", -1); // edit nothing
//            else {
//                intent.putExtra("mode", 1); //edit the content
//                intent.putExtra("content", et.getText().toString());
//                intent.putExtra("time", dateToStr());
//                intent.putExtra("id", id);
//                intent.putExtra("tag", tag);
//            }
//        }
//    }
//
//    public String dateToStr(){
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return simpleDateFormat.format(date);
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}