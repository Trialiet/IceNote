package org.trialiet.notedemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.trialiet.notedemo.R;
import org.trialiet.notedemo.adapter.NoteAdapter;
import org.trialiet.notedemo.util.DBAdapter;
import org.trialiet.notedemo.util.Note;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends Activity {
    private String loginUsername;
    private List<Note> noteList = new ArrayList<Note>();
    ListView listView;
    ArrayAdapter<Note> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initNote();
        Intent deliveredIntent = getIntent();
        loginUsername = deliveredIntent.getStringExtra("username");
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Note note = noteList.get(position);
                Intent intent = new Intent(ListActivity.this, NoteActivity.class);
                intent.putExtra("note", note);
                ListActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        //initNote();
        //listView.setAdapter(adapter);
        //listView.invalidate();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        //initNote();
        //listView.setAdapter(adapter);
        //listView.invalidate();
    }


    public void initNote(){
        if (loginUsername == null){
            loginUsername = "admin";
        }
        DBAdapter dbAdapter = new DBAdapter(ListActivity.this, loginUsername);
        dbAdapter.open();
        noteList = dbAdapter.getAllNotes();
        adapter = new ArrayAdapter<Note>(ListActivity.this,android.R.layout.simple_list_item_1, noteList);
        dbAdapter.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.create_new_note:
                Intent intent = new Intent(ListActivity.this, NoteActivity.class);
                ListActivity.this.startActivity(intent);
                break;
            default:
        }
        return true;
    }
}
