package com.example.todoapi.usecase.task.create;

import com.example.todoapi.usecase.core.InputData;

public class TaskCreateInputData implements InputData<TaskCreateOutputData> {
    private String title;

    public TaskCreateInputData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
