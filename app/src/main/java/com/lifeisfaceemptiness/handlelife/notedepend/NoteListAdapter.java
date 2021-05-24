package com.lifeisfaceemptiness.handlelife.notedepend;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lifeisfaceemptiness.handlelife.R;

import java.util.List;

public class NoteListAdapter extends BaseAdapter {

    private Context mContext;
    private List<NoteItem> noteItemList;
    private List<NoteItem> noteItemListBackup;

    public NoteListAdapter(Context context, List<NoteItem> noteItems) {
        this.mContext = context;
        this.noteItemList = noteItems;
        this.noteItemListBackup = this.noteItemList;
    }

    @Override
    public int getCount() {
        return noteItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NoteItemViewHolder viewHolder;
        View view;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        if (convertView == null) {
            view = View.inflate(mContext, R.layout.note_item_layout, null);
            viewHolder = new NoteItemViewHolder();
            viewHolder.tvTitle = view.findViewById(R.id.note_item_title);
            viewHolder.tvTime = view.findViewById(R.id.note_item_time);
            viewHolder.tvContent = view.findViewById(R.id.note_item_content);
            viewHolder.tvId = view.findViewById(R.id.note_item_id);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (NoteItemViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(noteItemList.get(position).getNoteTitle());
        viewHolder.tvTime.setText(noteItemList.get(position).getNoteCreateTime());
        viewHolder.tvContent.setText(noteItemList.get(position).getNoteContent());
        viewHolder.tvId.setText(String.valueOf(noteItemList.get(position).getNoteId()));

        return view;
    }

    private static class NoteItemViewHolder {
        TextView tvTitle;
        TextView tvTime;
        TextView tvContent;
        TextView tvId;
    }

}

