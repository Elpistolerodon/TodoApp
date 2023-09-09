package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.dao.TodoDAO;
import com.example.todoapp.model.Todo;

public class TodoDetailActivity extends AppCompatActivity {

    TextView todoDetailText;
    Button update, delete;
    CheckBox isCompletedCheckBox;
    TodoDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        todoDetailText = findViewById(R.id.todoDetailText);
        update = findViewById(R.id.idBtnUpdateCourse);
        delete = findViewById(R.id.DeleteTodoButton);
        isCompletedCheckBox = findViewById(R.id.isCompleted);
        dao = new TodoDAO(this);
        dao.open();

        String clickedTodoItem = getIntent().getStringExtra("clickedTodo");
        boolean completed = getIntent().getBooleanExtra("isCompleted", false);
        long id = getIntent().getLongExtra("id", 0L);

        todoDetailText.setText(clickedTodoItem );
        isCompletedCheckBox.setChecked(completed);

        update.setOnClickListener(view ->{
            int result = dao.updateTodo(new Todo(id, todoDetailText.getText().toString(), isCompletedCheckBox.isChecked()));
            if(result == 1){
                Toast.makeText(this, "Todo updated successfully", Toast.LENGTH_SHORT).show();
                this.finish();
            }else{
                Toast.makeText(this, "Failed to update Todo", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(view ->{
            int result = dao.deleteTodo(id);
            if(result == 1){
                Toast.makeText(this, "Todo deleted successfully", Toast.LENGTH_SHORT).show();
                this.finish();
            }else{
                Toast.makeText(this, "Failed to delete Todo", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onDestroy() {
        dao.close();
        super.onDestroy();
    }
}