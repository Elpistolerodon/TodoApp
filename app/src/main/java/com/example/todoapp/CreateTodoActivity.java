package com.example.todoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.dao.MyDBHelper;
import com.example.todoapp.dao.TodoDAO;
import com.example.todoapp.model.Todo;

public class CreateTodoActivity extends AppCompatActivity {

    private TodoDAO todoDAO;
    EditText editText;

    Button saveTodoButton;
    Button DeleteTodoButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        todoDAO = new TodoDAO(this);
        todoDAO.open();
        editText = findViewById(R.id.editTextText);

        saveTodoButton = findViewById(R.id.saveTodoBtn);
       // DeleteTodoButton = findViewById(R.id.DeleteTodoButton);

        saveTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateTodoActivity.this, MainActivity.class);
                String todoTitle = editText.getText().toString();
                Todo todo = new Todo(todoTitle, false);
                long rowId= todoDAO.insertTodoItem(todo);
                if(rowId>0){
                    Toast.makeText(CreateTodoActivity.this,"Todo added successfully", Toast.LENGTH_LONG).show();
                    CreateTodoActivity.this.finish();
                } else {
                    Toast.makeText(CreateTodoActivity.this,"Failed to add Todo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //todoDAO.close();
    }
}
