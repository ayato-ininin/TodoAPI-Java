package com.example.todoapi.usecase.task.delete;

import com.example.todoapi.usecase.core.InputData;

public class TaskDeleteInputData implements InputData<TaskDeleteOutputData> {
    private Long id;

    public TaskDeleteInputData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
