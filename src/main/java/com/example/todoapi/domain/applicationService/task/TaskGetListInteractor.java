package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.models.Task;
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
    @Autowired
    public TaskGetListInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public TaskGetListOutputData handle(TaskGetListInputData inputData) {
        List<Task> tasks = taskRepository.selectList(inputData.getLimit(), inputData.getOffset());
        List<TaskData> taskData = tasks.stream()
                .map(task -> new TaskData(task.getId(), task.getTitle()))
                .collect(Collectors.toList());
        return new TaskGetListOutputData(taskData);
    }
}
