package com.example.todoapi.usecase.task.create;

import com.example.todoapi.usecase.core.UseCase;

public interface TaskCreateUseCase extends UseCase<TaskCreateInputData, TaskCreateOutputData> {
    TaskCreateOutputData handle(TaskCreateInputData inputData);
}
