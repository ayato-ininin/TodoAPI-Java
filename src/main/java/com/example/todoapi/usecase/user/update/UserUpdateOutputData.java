package com.example.todoapi.usecase.user.update;

import com.example.todoapi.usecase.core.OutputData;
import com.example.todoapi.usecase.user.common.UserData;

public class UserUpdateOutputData implements OutputData {
    private UserData userData;

    public UserUpdateOutputData(UserData userData) {
        this.userData = userData;
    }

    //JacksonがTaskCreateOutputDataクラスのインスタンスをデシリアライズ（JSONからJavaオブジェクトに変換）するstub用
    private UserUpdateOutputData() { }
    public UserData getUserData() {
        return userData;
    }
}