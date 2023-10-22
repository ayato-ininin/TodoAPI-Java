package com.example.todoapi.usecase.task.getDetail;

import com.example.todoapi.usecase.core.OutputData;
import com.example.todoapi.usecase.task.common.TaskData;

import java.util.Optional;

public class TaskGetDetailOutputData implements OutputData {
    private Optional<TaskData> taskData;

    public TaskGetDetailOutputData(Optional<TaskData> taskData) {
        this.taskData = taskData;
    }

    //JacksonがTaskCreateOutputDataクラスのインスタンスをデシリアライズ（JSONからJavaオブジェクトに変換）するstub用
    private TaskGetDetailOutputData() { }
    public Optional<TaskData> getTaskData() {
        return taskData;
    }
}
