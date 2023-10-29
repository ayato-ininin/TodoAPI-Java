package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.applicationService.task.common.ConvertEntity;
import com.example.todoapi.domain.applicationService.task.common.TaskEntityNotFoundException;
import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.domain.model.task.TaskTitle;
import com.example.todoapi.domain.model.task.specification.TaskFullSpecification;
import com.example.todoapi.usecase.task.common.TaskData;
import com.example.todoapi.usecase.task.update.TaskUpdateInputData;
import com.example.todoapi.usecase.task.update.TaskUpdateOutputData;
import com.example.todoapi.usecase.task.update.TaskUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class TaskUpdateInteractor implements TaskUpdateUseCase {
    private final TaskRepository taskRepository;
    private final ConvertEntity convertEntity;
    private final TaskFullSpecification taskFullSpecification;
    @Autowired
    public TaskUpdateInteractor(
            TaskRepository taskRepository,
            ConvertEntity convertEntity,
            TaskFullSpecification taskFullSpecification
    ) {
        this.taskRepository = taskRepository;
        this.convertEntity = convertEntity;
        this.taskFullSpecification = taskFullSpecification;
    }
    @Override
    public TaskUpdateOutputData handle(TaskUpdateInputData inputData) {
        TaskEntity taskEntity = taskRepository.find(inputData.getId())
                .orElseThrow(() -> new TaskEntityNotFoundException(inputData.getId()));
        TaskTitle title = new TaskTitle(inputData.getTitle());
        taskEntity.changeTitle(title);
        if (taskFullSpecification.isSatisfiedBy(taskEntity)) {
            taskEntity.changeAssignedUserList(
                   convertEntity.convertAssignedUserList(inputData.getAssignedUserList())
            );
            taskRepository.update(taskEntity);
        }
        TaskData taskData = convertEntity.convertTaskData(taskEntity);
        return new TaskUpdateOutputData(taskData);
    }
}
