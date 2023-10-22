package com.example.todoapi.usecase.task.update;

import com.example.todoapi.usecase.core.OutputData;
import com.example.todoapi.usecase.task.common.TaskData;

public class TaskUpdateOutputData implements OutputData {
    private TaskData taskData;

    public TaskUpdateOutputData(TaskData taskData) {
        this.taskData = taskData;
    }

    //JacksonがTaskCreateOutputDataクラスのインスタンスをデシリアライズ（JSONからJavaオブジェクトに変換）するstub用
    private TaskUpdateOutputData() { }
    public TaskData getTaskData() {
        return taskData;
    }
}
