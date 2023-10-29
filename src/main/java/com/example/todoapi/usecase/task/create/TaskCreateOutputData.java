package com.example.todoapi.usecase.task.create;

import com.example.todoapi.usecase.core.OutputData;

import java.util.ArrayList;
import java.util.List;

public class TaskCreateOutputData implements OutputData {
    private long id;
    private String title;

    private List<Long> assignedUserList;

    public TaskCreateOutputData(long id, String title, List<Long> assignedUserList) {
        this.id = id;
        this.title = title;
        this.assignedUserList = assignedUserList;
    }

    //JacksonがTaskCreateOutputDataクラスのインスタンスをデシリアライズ（JSONからJavaオブジェクトに変換）するstub用
    public TaskCreateOutputData() { }
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Long> getAssignedUserList() {
        return new ArrayList<>(assignedUserList); // Return a copy to ensure immutability
    }
}
