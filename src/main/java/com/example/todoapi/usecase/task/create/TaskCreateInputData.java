package com.example.todoapi.usecase.task.create;

import com.example.todoapi.usecase.core.InputData;

import java.util.ArrayList;
import java.util.List;

public class TaskCreateInputData implements InputData<TaskCreateOutputData> {
    private String title;

    private List<Long> assignedUserList;

    public TaskCreateInputData(String title, List<Long> assignedUserList) {
        this.title = title;
        this.assignedUserList = assignedUserList;
    }

    public String getTitle() {
        return title;
    }

    public List<Long> getAssignedUserList() {
        return new ArrayList<>(assignedUserList); // Return a copy to ensure immutability
    }
}
