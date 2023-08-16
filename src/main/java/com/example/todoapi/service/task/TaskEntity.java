package com.example.todoapi.service.task;

public class TaskEntity {

    private long id;
    private String title;

    public TaskEntity(long id, String title) {
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
