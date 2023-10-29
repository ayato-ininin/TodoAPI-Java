package com.example.todoapi.usecase.task.update;

import com.example.todoapi.usecase.core.InputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailOutputData;

import java.util.ArrayList;
import java.util.List;

public class TaskUpdateInputData implements InputData<TaskUpdateOutputData> {
    private Long id;
    private String title;

    private List<Long> assignedUserList;

    public TaskUpdateInputData(Long id, String title, List<Long> assignedUserList) {
        this.id = id;
        this.title = title;
        this.assignedUserList = assignedUserList;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public List<Long> getAssignedUserList() {
        return new ArrayList<>(assignedUserList); // Return a copy to ensure immutability
    }
}
