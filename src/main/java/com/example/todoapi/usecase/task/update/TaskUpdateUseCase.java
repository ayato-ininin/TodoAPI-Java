package com.example.todoapi.usecase.task.update;

import com.example.todoapi.usecase.core.UseCase;
import com.example.todoapi.usecase.task.create.TaskCreateInputData;

public interface TaskUpdateUseCase extends UseCase<TaskUpdateInputData, TaskUpdateOutputData> {
    TaskUpdateOutputData handle(TaskUpdateInputData inputData);
}
