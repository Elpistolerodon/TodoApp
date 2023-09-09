package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todoapp.dao.TodoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.example.todoapp.adapters.TodoAdapter;
import com.example.todoapp.model.Todo;

public class MainActivity extends AppCompatActivity {

    private TodoDAO todoDAO;
    private RecyclerView.Adapter adapterTodo;

    private RecyclerView recyclerView;

    private FloatingActionButton createTodoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoDAO = new TodoDAO(this);
        todoDAO.open();
        createTodoBtn = findViewById(R.id.createTodoBtn);

        initRecyclerView();

        createTodoBtn.setOnClickListener(view -> {
            Intent intent =new Intent(MainActivity.this, CreateTodoActivity.class);
            startActivity(intent);
        });
    }
    private void initRecyclerView(){
        ArrayList<Todo> todos = new ArrayList();
       todos.addAll(todoDAO.getAllTodos());

        recyclerView = findViewById(R.id.todolist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false ));

        adapterTodo = new TodoAdapter(todos);
        recyclerView.setAdapter(adapterTodo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        todoDAO.close();
    }
}