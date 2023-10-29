package com.example.todoapi.models;

// DB保存用のEntity
// ORMには使用していない
public class Task {
    private long id;
    private String title;
    private String assignedUserList;// カンマ区切り

    public Task(long id, String title, String assignedUserList) {
        this.id = id;
        this.title = title;
        this.assignedUserList = assignedUserList;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignedUserList() {
        return assignedUserList;
    }
}
