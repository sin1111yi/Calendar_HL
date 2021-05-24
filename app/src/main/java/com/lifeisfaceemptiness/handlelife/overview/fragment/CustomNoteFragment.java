package com.lifeisfaceemptiness.handlelife.overview.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lifeisfaceemptiness.handlelife.R;
import com.lifeisfaceemptiness.handlelife.create.fragment.CreateNoteFragment;
import com.lifeisfaceemptiness.handlelife.note.CRUD;
import com.lifeisfaceemptiness.handlelife.note.EditActivity;
import com.lifeisfaceemptiness.handlelife.note.Note;
import com.lifeisfaceemptiness.handlelife.note.NoteAdapter;
import com.lifeisfaceemptiness.handlelife.note.NoteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CustomNoteFragment extends Fragment implements AdapterView.OnItemClickListener {

    View rootView;
    //======================================================================
    private NoteDatabase dbHelper;

    private Context mContext;

    final String TAG = "CustomNodeFragment";
    Button btn;
    Button btn2;
    FloatingActionButton btn3;

    TextView tv;
    private ListView lv;
    private EditText search_note;

    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<Note>();
    private Toolbar myToolbar;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        int returnMode;
        long note_Id;
        returnMode = data.getExtras().getInt("mode", -1);
        note_Id = data.getExtras().getLong("id", 0);

        if (returnMode == 1) {  //update current note

            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);

            Note newNote = new Note(content, time, tag);
            newNote.setId(note_Id);
            CRUD op = new CRUD(mContext);
            op.open();
            op.updateNote(newNote);
            op.close();
        } else if (returnMode == 0) {  // create new note
            String content = data.getExtras().getString("content");
            String time = data.getExtras().getString("time");
            int tag = data.getExtras().getInt("tag", 1);
            Note newNote = new Note(content, time, tag);
            CRUD op = new CRUD(mContext);
            op.open();
            op.addNote(newNote);
            op.close();
        }else if (returnMode == 2) { // delete
            Note curNote = new Note();
            curNote.setId(note_Id);
            CRUD op = new CRUD(mContext);
            op.open();
            op.removeNote(curNote);
            op.close();
        }else{

        }
        refreshListView();
        super.onActivityResult(requestCode, resultCode, data);


    }


    public void refreshListView(){

        CRUD op = new CRUD(mContext);
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







    public CustomNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment CustomNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomNoteFragment newInstance() {
        CustomNoteFragment fragment = new CustomNoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


        if (getArguments() != null) {
        }

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_note_create, container, false);
        }

        setHasOptionsMenu(true);


        //========================
        mContext=getContext();

        btn = rootView.findViewById(R.id.fab);
        btn2 = rootView.findViewById(R.id.fab2);


        search_note = rootView.findViewById((R.id.search_note));

        lv = (ListView)rootView.findViewById(R.id.lv);

        adapter = new NoteAdapter(getContext(), noteList);
        refreshListView();
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(this);

        //Log.d(TAG, "onClick: click");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: click");
                Intent intent = new Intent(mContext, EditActivity.class);
                intent.putExtra("mode", 4);
                startActivityForResult(intent, 0);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence newText, int arg1, int arg2, int arg3) {
                // 文本实时变化的回调
                adapter.getFilter().filter(newText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        return rootView;
    }



    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }




}