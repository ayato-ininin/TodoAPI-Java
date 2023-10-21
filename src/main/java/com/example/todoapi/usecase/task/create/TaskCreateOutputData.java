package com.example.todoapi.usecase.task.create;

import com.example.todoapi.usecase.core.OutputData;

public class TaskCreateOutputData implements OutputData {
    private long id;
    private String title;

    public TaskCreateOutputData(long id, String title) {
        this.id = id;
        this.title = title;
    }

    //JacksonがTaskCreateOutputDataクラスのインスタンスをデシリアライズ（JSONからJavaオブジェクトに変換）するstub用
    public TaskCreateOutputData() { }
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
