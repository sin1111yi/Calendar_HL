package com.example.handlelife.overview.fragment;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.handlelife.R;
import com.example.handlelife.note.CRUD;
import com.example.handlelife.note.EditActivity;
import com.example.handlelife.note.Note;
import com.example.handlelife.note.NoteAdapter;
import com.example.handlelife.note.NoteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomNoteFragment extends Fragment implements AdapterView.OnItemClickListener {

    View rootView;
    //======================================================================
    private NoteDatabase dbHelper;

    private Context mContext;

    final String TAG = "CustomNodeFragment";
    FloatingActionButton btn;
    TextView tv;
    private ListView lv;

    private NoteAdapter adapter;
    private List<Note> noteList = new ArrayList<Note>();
    private Toolbar myToolbar;



    //接收startActivtyForResult的结果
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
        /*String content = data.getStringExtra("content");
        String time = data.getStringExtra("time");
        Note note = new Note(content, time, 1);
        CRUD op = new CRUD(context);
        op.open();
        op.addNote(note);
        op.close();
        refreshListView();*/

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG, "111");

        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);


        //search setting
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();

        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_clear:
                new AlertDialog.Builder(mContext)
                        .setMessage("删除全部吗？")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper = new NoteDatabase(mContext);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.delete("notes", null, null);
                                db.execSQL("update sqlite_sequence set seq=0 where name='notes'");
                                refreshListView();
                            }
                        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
                break;

        }
        return super.onOptionsItemSelected(item);
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


//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        switch (parent.getId()) {
//            case R.id.lv:
//                Note curNote = (Note) parent.getItemAtPosition(position);
//                Intent intent = new Intent(CustomNoteFragment.this, EditActivity.class);
//                intent.putExtra("content", curNote.getContent());
//                intent.putExtra("id", curNote.getId());
//                intent.putExtra("time", curNote.getTime());
//                intent.putExtra("mode", 3);     // MODE of 'click to edit'
//                intent.putExtra("tag", curNote.getTag());
//                startActivityForResult(intent, 1);      //collect data from edit
//                Log.d(TAG, "onItemClick: " + position);
//                break;
//        }
//    }
//






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


        //setContentView(R.layout.fragment_custom_note);
        if (getArguments() != null) {
        }

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_custom_note, container, false);
        }

        setHasOptionsMenu(true);

        //========================
        mContext=getContext();
        btn = (FloatingActionButton) rootView.findViewById(R.id.fab);
        //tv = findViewById(R.id.tv);
        lv = (ListView)rootView.findViewById(R.id.lv);
        //myToolbar = rootView.findViewById(R.id.myToolbar);
        adapter = new NoteAdapter(getContext(), noteList);
        refreshListView();
        lv.setAdapter(adapter);
//        setSupportActionBar(myToolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //设置toolbar取代actionBar
//        myToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);

        lv.setOnItemClickListener(this);

        Log.d(TAG, "onClick: click");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: click");
//                Intent intent = new Intent(CustomNoteFragment.this, EditActivity.class);
//                intent.putExtra("mode", 4);
//                startActivityForResult(intent, 0);
            }
        });
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    //===========================================


//    @Override
//    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//    }




}