package com.example.todoapi.usecase.user.getDetail;

import com.example.todoapi.usecase.core.OutputData;
import com.example.todoapi.usecase.user.common.UserData;

import java.util.Optional;

public class UserGetDetailOutputData implements OutputData {
    private Optional<UserData> userData;

    public UserGetDetailOutputData(Optional<UserData> userData) {
        this.userData = userData;
    }

    //JacksonがTaskCreateOutputDataクラスのインスタンスをデシリアライズ（JSONからJavaオブジェクトに変換）するstub用
    private UserGetDetailOutputData() { }
    public Optional<UserData> getUserData() {
        return userData;
    }
}