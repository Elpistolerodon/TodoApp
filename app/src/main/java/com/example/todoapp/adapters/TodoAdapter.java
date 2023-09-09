package com.example.todoapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.TodoDetailActivity;

import java.util.ArrayList;

import com.example.todoapp.model.Todo;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {
    static ArrayList<Todo> todos;

    static Context context;
    private onItemClicklistener clicklistener;

    public TodoAdapter(ArrayList<Todo> todos) {this.todos = todos;}

    @NonNull
    @Override
    public TodoAdapter.ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_todo, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }
    public interface onItemClicklistener {
        void onItemClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.ViewHolder holder, int position){
        Todo todo = todos.get(position);
        holder.todoTitle.setText(todo.getTitle());
        holder.todoCheckBox.setChecked(todo.isComplete());
        holder.bind(todo, clicklistener);
    }
    @Override
    public int getItemCount(){return todos.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView todoTitle;

        CheckBox todoCheckBox;

        public ViewHolder (@NonNull View itemView){
            super(itemView);
            todoTitle = itemView.findViewById(R.id.todoTitle);
            todoCheckBox = itemView.findViewById(R.id.checkBoxCompleted);
        }
        public void bind(Todo todo, onItemClicklistener listener){
            todoTitle.setText((todo.getTitle()));
            todoCheckBox.setChecked(todo.isComplete());

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    Todo clickTodoItem = todos.get(position);

                    Intent intent = new Intent(itemView.getContext(), TodoDetailActivity.class);
                    intent.putExtra("clickedTodo", clickTodoItem.getTitle());
                    intent.putExtra("isCompleted", todoCheckBox.isChecked());
                    intent.putExtra("id", todo.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
