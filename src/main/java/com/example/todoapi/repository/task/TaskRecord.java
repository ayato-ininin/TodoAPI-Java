package com.example.todoapi.repository.task;

public class TaskRecord {

    private long id;
    private String title;

    public TaskRecord(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
