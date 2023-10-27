package com.example.todoapi.usecase.user.create;

import com.example.todoapi.usecase.core.OutputData;

public class UserCreateOutputData implements OutputData {
    private long id;
    private String name;

    public UserCreateOutputData(long id, String name) {
        this.id = id;
        this.name = name;
    }

    //JacksonがTaskCreateOutputDataクラスのインスタンスをデシリアライズ（JSONからJavaオブジェクトに変換）するstub用
    public UserCreateOutputData() { }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}