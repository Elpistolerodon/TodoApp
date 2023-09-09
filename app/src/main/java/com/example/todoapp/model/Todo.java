package com.example.todoapp.model;

public class Todo {
    private long id;

    private String title;

    private boolean isComplete;

    public Todo(String title, boolean isComplete){
        this.title = title;
        this.isComplete = isComplete;
    }

    public Todo(long id, String title, boolean isComplete){
        this.id = id;
        this.title = title;
        this.isComplete = isComplete;
    }

    public long getId() {return id;}

    public void setId(long id){this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title){this.title = title;}

    public boolean isComplete() { return isComplete; }

    public void setCompleted(boolean completed) {isComplete = completed;}
}
