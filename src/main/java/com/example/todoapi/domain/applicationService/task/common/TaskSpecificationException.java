package com.example.todoapi.domain.applicationService.task.common;

public class TaskSpecificationException extends RuntimeException{

    public TaskSpecificationException() {
        // super()は親クラスのコンストラクタを呼び出す
        super("TaskEntity is invalid. ");
    }
}
