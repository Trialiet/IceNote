package org.trialiet.notedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.trialiet.notedemo.R;
import org.trialiet.notedemo.util.Note;

import java.util.List;

/**
 * Created by Trialiet on 2016/4/27.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    private int resourceId;

    public NoteAdapter(Context context, int textViewResource, List<Note> objects) {
        super(context, textViewResource, objects);
        resourceId = textViewResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Note note = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.noteTitle = (TextView) view.findViewById(R.id.item_note_title);
            view.setTag(viewHolder);
        } else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.noteTitle.setText(note.getTitle().toString());
        return view;
    }

    class ViewHolder {
        TextView noteTitle;
        public ViewHolder(){
            noteTitle = null;
        }
    }

}
