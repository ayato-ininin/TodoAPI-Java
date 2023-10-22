package com.example.todoapi.usecase.task.getDetail;

import com.example.todoapi.usecase.core.InputData;

public class TaskGetDetailInputData implements InputData<TaskGetDetailOutputData> {
    private Long id;

    public TaskGetDetailInputData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
