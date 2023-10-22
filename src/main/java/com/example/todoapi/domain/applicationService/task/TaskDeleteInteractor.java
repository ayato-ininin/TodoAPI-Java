package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.applicationService.task.common.TaskEntityNotFoundException;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.usecase.task.delete.TaskDeleteInputData;
import com.example.todoapi.usecase.task.delete.TaskDeleteOutputData;
import com.example.todoapi.usecase.task.delete.TaskDeleteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class TaskDeleteInteractor implements TaskDeleteUseCase {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskDeleteInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public TaskDeleteOutputData handle(TaskDeleteInputData inputData) {
        taskRepository.find(inputData.getId())
                .orElseThrow(() -> new TaskEntityNotFoundException(inputData.getId()));
        taskRepository.delete(inputData.getId());
        return new TaskDeleteOutputData();
    }
}
