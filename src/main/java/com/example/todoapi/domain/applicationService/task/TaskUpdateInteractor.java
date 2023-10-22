package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.domain.model.task.TaskTitle;
import com.example.todoapi.service.task.TaskEntityNotFoundException;
import com.example.todoapi.usecase.task.common.TaskData;
import com.example.todoapi.usecase.task.update.TaskUpdateInputData;
import com.example.todoapi.usecase.task.update.TaskUpdateOutputData;
import com.example.todoapi.usecase.task.update.TaskUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class TaskUpdateInteractor implements TaskUpdateUseCase {
    private final TaskRepository taskRepository;
    @Autowired
    public TaskUpdateInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public TaskUpdateOutputData handle(TaskUpdateInputData inputData) {
        TaskEntity taskEntity = taskRepository.find(inputData.getId())
                .orElseThrow(() -> new TaskEntityNotFoundException(inputData.getId()));
        TaskTitle title = new TaskTitle(inputData.getTitle());
        taskEntity.changeTitle(title);
        taskRepository.update(taskEntity);
        TaskData taskData = new TaskData(
                inputData.getId(),
                title.getValue()
        );
        return new TaskUpdateOutputData(taskData);
    }
}
