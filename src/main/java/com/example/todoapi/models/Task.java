package com.example.todoapi.models;

// DB保存用のEntity
// ORMには使用していない
public class Task {
    private String id;
    private String title;

    public Task(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
