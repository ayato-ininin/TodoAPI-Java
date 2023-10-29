package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.applicationService.task.common.ConvertEntity;
import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.usecase.task.common.TaskData;
import com.example.todoapi.usecase.task.getList.TaskGetListInputData;
import com.example.todoapi.usecase.task.getList.TaskGetListOutputData;
import com.example.todoapi.usecase.task.getList.TaskGetListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class TaskGetListInteractor implements TaskGetListUseCase {

    private final TaskRepository taskRepository;
    private final ConvertEntity convertEntity;
    @Autowired
    public TaskGetListInteractor(
            TaskRepository taskRepository,
            ConvertEntity convertEntity
    ) {
        this.taskRepository = taskRepository;
        this.convertEntity = convertEntity;
    }
    @Override
    public TaskGetListOutputData handle(TaskGetListInputData inputData) {
        List<TaskEntity> tasks = taskRepository.findAll(inputData.getLimit(), inputData.getOffset());
        List<TaskData> taskData = tasks.stream()
                .map(convertEntity::convertTaskData)
                .collect(Collectors.toList());
        return new TaskGetListOutputData(taskData);
    }
}
