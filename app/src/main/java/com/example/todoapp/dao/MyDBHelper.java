package com.example.todoapp.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    // todo table and columns
    private static final String TABLE_TODO = "todo";
    private static final String COLUMN_TO = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_IS_COMPLETED = "is_completed";

    public MyDBHelper (Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_TODO + "(" +
                COLUMN_TO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " +
                COLUMN_IS_COMPLETED + " INTEGER )";
        sqLiteDatabase.execSQL(createTableQuery);
    }
//    public void updateTodoActivity(String TABLE_TODO) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(TABLE_TODO, TABLE_TODO);
//
//        db.update(TABLE_TODO, values, "name=?", new String[]{TABLE_TODO});
//        db.close();
//    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
    }
}
