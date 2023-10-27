package com.example.todoapi.usecase.user.delete;

import com.example.todoapi.usecase.core.InputData;

public class UserDeleteInputData implements InputData<UserDeleteOutputData> {
    private Long id;

    public UserDeleteInputData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}