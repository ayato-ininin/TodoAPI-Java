package com.example.todoapi.usecase.user.common;

public class UserData {
    private long id;
    private String name;

    public UserData(long id, String name) {
        this.id = id;
        this.name = name;
    }
    private UserData() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
