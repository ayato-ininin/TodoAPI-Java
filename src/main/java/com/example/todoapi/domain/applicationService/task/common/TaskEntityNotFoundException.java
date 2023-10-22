package com.example.todoapi.domain.applicationService.task.common;

public class TaskEntityNotFoundException extends RuntimeException{

    // 取得に失敗したidを保持する
    private long taskId;

    public TaskEntityNotFoundException(long taskId) {
        // super()は親クラスのコンストラクタを呼び出す
        super("TaskEntity id not found. [taskId=" + taskId + "]");
        this.taskId = taskId;
    }
}
