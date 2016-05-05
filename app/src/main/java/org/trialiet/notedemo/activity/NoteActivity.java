package org.trialiet.notedemo.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import org.trialiet.notedemo.R;
import org.trialiet.notedemo.util.DBAdapter;
import org.trialiet.notedemo.util.Note;

/**
 * Created by Trialiet on 2016/4/27.
 */
public class NoteActivity extends Activity {
    Note note;
    private EditText title;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        title = (EditText) findViewById(R.id.et_note_title);
        content = (EditText) findViewById(R.id.et_note_content);
        Intent intent = getIntent();
        note = (Note) intent.getSerializableExtra("note");
        if (note == null){
            title.setText("");
            content.setText("");
        }
        else{
            title.setText(note.getTitle());
            content.setText(note.getContent());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.save_note) {
            DBAdapter dbAdapter = new DBAdapter(NoteActivity.this, null);
            dbAdapter.open();
            String noteTitle = title.getText().toString();
            String noteContent = content.getText().toString();
            if (note == null){
                dbAdapter.insert(noteTitle, noteContent);
            }
            else{
                if (dbAdapter.update(note.getId(), noteTitle, noteContent))
                    Toast.makeText(NoteActivity.this, "Update Success", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(NoteActivity.this, "Update Fail", Toast.LENGTH_LONG).show();
            }
            dbAdapter.close();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}