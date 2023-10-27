package com.example.todoapi.usecase.user.getDetail;

import com.example.todoapi.usecase.core.InputData;

public class UserGetDetailInputData implements InputData<UserGetDetailOutputData> {
    private Long id;

    public UserGetDetailInputData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}