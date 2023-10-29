package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.applicationService.task.common.ConvertEntity;
import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.usecase.task.common.TaskData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailInputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailOutputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class TaskGetDetailInteractor implements TaskGetDetailUseCase {
    private final TaskRepository taskRepository;
    private final ConvertEntity convertEntity;
    @Autowired
    public TaskGetDetailInteractor(
            TaskRepository taskRepository,
            ConvertEntity convertEntity
    ) {
        this.taskRepository = taskRepository;
        this.convertEntity = convertEntity;
    }
    @Override
    public TaskGetDetailOutputData handle(TaskGetDetailInputData inputData) {
        Optional<TaskEntity> taskEntity = taskRepository.find(inputData.getId());
        Optional<TaskData> taskData = taskEntity
                .map(convertEntity::convertTaskData);
        return new TaskGetDetailOutputData(taskData);
    }
}
