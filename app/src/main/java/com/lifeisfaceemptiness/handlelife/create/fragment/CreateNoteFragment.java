package com.lifeisfaceemptiness.handlelife.create.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lifeisfaceemptiness.handlelife.R;

public class CreateNoteFragment extends Fragment {

    EditText et;
    View rootView;

    private static final String TAG = "CreateNoteFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "CreateNoteFragment create");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_note_show, container, false);
        }
        et = rootView.findViewById(R.id.et1);
        return rootView;
    }


    /**
     * 设置接口，把当前的 Fragment 的数据的传给Activity
     */
    public interface ISendNoteDataListener {
        void postNoteData(String s);
    }

    public void sendNoteData(ISendNoteDataListener iSendNoteDataListener) {
        iSendNoteDataListener.postNoteData(et.getText().toString().trim());
    }

}