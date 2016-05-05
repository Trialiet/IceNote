package org.trialiet.notedemo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trialiet on 2016/4/27.
 */
public class DBAdapter{
    static String CREATE_TABLE;
    static final String KEY_TITLE = "title";
    static final String KEY_CONTENT = "content";
    static final String KEY_ID = "_id";
    static final String DATABASE_NAME = "IceNoteDB";
    static String DATABASE_TABLE_NAME;
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx, String username){
        this.context = ctx;
        DATABASE_TABLE_NAME = username==null?"admin":username;
        CREATE_TABLE = "create table " + DATABASE_TABLE_NAME + " ("
                + "_id integer primary key autoincrement, "
                + "title text, "
                + "content text)";
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(CREATE_TABLE);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        DBHelper.close();
    }

    public long insert(String title, String content){
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_CONTENT, content);
        return db.insert(DATABASE_TABLE_NAME, null, values);
    }

    public boolean delete(long id){
        return db.delete(DATABASE_TABLE_NAME, KEY_ID + "=" + id, null) > 0;
    }

    public List<Note> getAllNotes(){
        List<Note> list = new ArrayList<Note>();
        String title, content;
        long id;
        Cursor cursor = db.query(DATABASE_TABLE_NAME, new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT}, null, null, null, null, null);
	 if (cursor.moveToNext() == false){
	     return null;
	 }
	 else
        while (cursor.moveToNext() != false){
            title = cursor.getString(cursor.getColumnIndex("title"));
            content = cursor.getString(cursor.getColumnIndex("content"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new Note(id, title, content));
        }
        cursor.close();
        return list;
    }

    public Note getNote(long id) throws SQLException{
        Cursor cursor = db.query(true, DATABASE_TABLE_NAME, new String[] {KEY_ID, KEY_TITLE, KEY_CONTENT},
                KEY_ID + "=" + id, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        long _id = cursor.getInt(cursor.getColumnIndex("_id"));
        Note note = new Note(_id, title, content);
        cursor.close();
        return note;
    }

    public boolean update(long id, String title, String content){
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_CONTENT, content);
        return db.update(DATABASE_TABLE_NAME, values, KEY_ID + "=" + id, null) > 0;
    }

    public boolean CreateUserDataTable(String username){
        try{
            String sql = "create table " + username +" ("
                    + "_id integer primary key autoincrement, "
                    + "title text, "
                    + "content text)";
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
