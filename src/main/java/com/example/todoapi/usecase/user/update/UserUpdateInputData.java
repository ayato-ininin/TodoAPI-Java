package com.example.todoapi.usecase.user.update;

import com.example.todoapi.usecase.core.InputData;

public class UserUpdateInputData implements InputData<UserUpdateOutputData> {
    private Long id;
    private String name;

    public UserUpdateInputData(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}