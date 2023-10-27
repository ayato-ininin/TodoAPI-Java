package com.example.todoapi.usecase.user.create;

import com.example.todoapi.usecase.core.InputData;

public class UserCreateInputData implements InputData<UserCreateOutputData> {

    private String name;

    public UserCreateInputData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}