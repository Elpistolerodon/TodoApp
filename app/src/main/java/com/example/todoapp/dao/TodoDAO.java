package com.example.todoapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.todoapp.model.Todo;

import java.util.ArrayList;

public class TodoDAO {
    private MyDBHelper dbHelper;

    private SQLiteDatabase database;

    public TodoDAO(Context context){
        dbHelper = new MyDBHelper(context);
    }
    public void open()throws SQLException{
        database = dbHelper.getWritableDatabase();
    }
    public long insertTodoItem(Todo todoItem){
        ContentValues values = new ContentValues();
        values.put("title", todoItem.getTitle());
        values.put("is_completed", todoItem.isComplete() ? 1 : 0);
        long rowID = database.insert("todo", null,values);
        if(rowID != -1) {
            todoItem.setId(rowID);
        }
        return rowID;
    }

    public ArrayList<Todo> getAllTodos(){
        ArrayList<Todo> todoItemList = new ArrayList<>();
        Cursor cursor = database.query("todo", null, null, null, null, null, null);

        if (cursor != null){
            while (cursor.moveToNext()){
               int id = cursor.getInt(0);
               String title = cursor.getString(1);
               int is_completedInt = cursor.getInt(2);

                boolean isCompleted = (is_completedInt == 1);
                Todo todo = new Todo(id, title, isCompleted);
                todoItemList.add(todo);
            }
            cursor.close();
        }
        return todoItemList;

    }

    public int updateTodo(Todo todo) {

        // calling a method to get writable database.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("is_completed", todo.isComplete() ? 1 : 0);
        values.put("title", todo.getTitle());

        int result = database.update("todo", values, "id=?", new String[]{String.valueOf(todo.getId())});

        return result;
    }

    public int deleteTodo(Long id) {

        int result = database.delete("todo", "id=?", new String[]{String.valueOf(id)});
        return result;
    }

        public void close(){dbHelper.close();}


}
