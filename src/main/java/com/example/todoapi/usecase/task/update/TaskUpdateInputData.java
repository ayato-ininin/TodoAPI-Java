package com.example.todoapi.usecase.task.update;

import com.example.todoapi.usecase.core.InputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailOutputData;

public class TaskUpdateInputData implements InputData<TaskUpdateOutputData> {
    private Long id;
    private String title;

    public TaskUpdateInputData(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
