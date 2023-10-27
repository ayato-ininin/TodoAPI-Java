package com.example.todoapi.usecase.user.getList;

import com.example.todoapi.usecase.core.OutputData;
import com.example.todoapi.usecase.user.common.UserData;

import java.util.ArrayList;
import java.util.List;

public class UserGetListOutputData implements OutputData {
    private List<UserData> users;

    public UserGetListOutputData(List<UserData> users) {
        this.users = users;
    }

    private UserGetListOutputData() {
        this.users = new ArrayList<>();
    }

    public List<UserData> getUsers() {
        return users;
    }
}