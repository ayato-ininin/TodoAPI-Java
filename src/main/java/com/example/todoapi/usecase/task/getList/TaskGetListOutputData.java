package com.example.todoapi.usecase.task.getList;

import com.example.todoapi.usecase.core.OutputData;
import com.example.todoapi.usecase.task.common.TaskData;

import java.util.ArrayList;
import java.util.List;

public class TaskGetListOutputData implements OutputData {
    private List<TaskData> tasks;

    public TaskGetListOutputData(List<TaskData> tasks) {
        this.tasks = tasks;
    }

    private TaskGetListOutputData() {
        this.tasks = new ArrayList<>();
    }

    public List<TaskData> getTasks() {
        return tasks;
    }
}
