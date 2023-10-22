package com.example.todoapi.usecase.task.common;

public class TaskData {
    private long id;
    private String title;

    public TaskData(long id, String title) {
        this.id = id;
        this.title = title;
    }
    private TaskData() {}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;

    }
}
