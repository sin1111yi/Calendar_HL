package com.lifeisfaceemptiness.handlelife.overview.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateNoteFragment;
import com.lifeisfaceemptiness.handlelife.note.EditActivity;
import com.lifeisfaceemptiness.handlelife.note.Note;
import com.lifeisfaceemptiness.handlelife.note.NoteAdapter;
import com.lifeisfaceemptiness.handlelife.note.NoteDatabase;
import com.lifeisfaceemptiness.handlelife.note.NoteMessage;
import com.lifeisfaceemptiness.handlelife.note.Note_crud;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CustomNoteFragment extends Fragment implements AdapterView.OnItemClickListener {

    View rootView;
    //======================================================================
    private NoteDatabase dbHelper;

    private int tag = 1;


    private Context mContext;

    final String TAG = "TAG";
    //Button btn;
    Button btn2;
    FloatingActionButton btn3;


    TextView tv;
    private ListView lv;
    private EditText search_note;

    public NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<Note>();
    private Toolbar myToolbar;


    CreateNoteFragment mCreateNoteFragment = new CreateNoteFragment();

    //接收startActivtyForResult的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        int returnMode;
        long note_Id;
        returnMode = data.getExtras().getInt("mode", -1);
        note_Id = data.getExtras().getLong("id", 0);

        if (returnMode == 0) {  //新
            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            Note_crud op = new Note_crud(mContext);
            op.open();
            op.addNote(newNote);
            op.close();


        } else if (returnMode == 1) {  // 更新
            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            newNote.setId(note_Id);
            Note_crud op = new Note_crud(mContext);
            op.open();
            op.updateNote(newNote);
            op.close();
        } else if (returnMode == 2) { // 删除
            Note curNote = new Note();
            curNote.setId(note_Id);
            Note_crud op = new Note_crud(mContext);
            op.open();
            op.removeNote(curNote);
            op.close();
        } else {

        }
        refreshListView();
        super.onActivityResult(requestCode, resultCode, data);


    }

    public void refreshListView() {

        Note_crud op = new Note_crud(mContext);
        op.open();
        // set adapter
        if (noteList.size() > 0) noteList.clear();
        noteList.addAll(op.getAllNotes());
        op.close();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv:
                Note curNote = (Note) parent.getItemAtPosition(position);
                // TODO：这里是有问题的？
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("content", curNote.getContent());
                intent.putExtra("id", curNote.getId());
                intent.putExtra("time", curNote.getTime());
                intent.putExtra("mode", 3);     // MODE of 'click to edit'
                intent.putExtra("tag", curNote.getTag());
                startActivityForResult(intent, 1);      //collect data from edit
                Log.d(TAG, "onItemClick: " + position);
                break;
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d("TAG", "KAIQI ");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_note_create, container, false);
        }
        EventBus.getDefault().register(this);
        setHasOptionsMenu(true);
        mContext = getContext();
        btn2 = rootView.findViewById(R.id.fab2);
        search_note = rootView.findViewById(R.id.search_note);
        lv = (ListView) rootView.findViewById(R.id.lv);
        adapter = new NoteAdapter(getContext(), noteList);
        refreshListView();
        lv.setAdapter(adapter);
        /// item 的点击事件
        lv.setOnItemClickListener(this);
        Log.d(TAG, "onClick: click");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setMessage("删除全部吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper = new NoteDatabase(mContext);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.delete("notes", null, null);
                                db.execSQL("update sqlite_sequence set seq=0 where name='notes'");
                                refreshListView();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
        });

        search_note.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // 文本实时变化的回调
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // 当文本被改变前的回调
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 文本变化以后的回调
            }
        });

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Subscribe
    public void onEvent(NoteMessage data) {
        Log.d("yangchao", "nsklsnslnskls");
        if (data.getIsSave() == 1 && !data.getContent().isEmpty()) {
            Log.d(TAG, data.toString());
            Note newNote = new Note(data.getContent(), dateToStr(), tag);
            Note_crud op = new Note_crud(mContext);
            op.open();
            op.addNote(newNote);
            op.close();
            Log.d(TAG, "wancheng");
            refreshListView();
        }
    }

    public String dateToStr() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}