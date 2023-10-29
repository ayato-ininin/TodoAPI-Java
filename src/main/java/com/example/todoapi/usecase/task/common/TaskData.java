package com.example.todoapi.usecase.task.common;

import java.util.ArrayList;
import java.util.List;

public class TaskData {
    private long id;
    private String title;

    private List<Long> assignedUserList;

    public TaskData(long id, String title, List<Long> assignedUserList) {
        this.id = id;
        this.title = title;
        this.assignedUserList = assignedUserList;
    }
    private TaskData() {}

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
