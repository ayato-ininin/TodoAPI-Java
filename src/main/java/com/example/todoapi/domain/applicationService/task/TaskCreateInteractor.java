package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.applicationService.task.common.ConvertEntity;
import com.example.todoapi.domain.applicationService.task.common.TaskSpecificationException;
import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskId;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.domain.model.task.TaskTitle;
import com.example.todoapi.domain.model.task.specification.TaskFullSpecification;
import com.example.todoapi.usecase.task.create.TaskCreateInputData;
import com.example.todoapi.usecase.task.create.TaskCreateOutputData;
import com.example.todoapi.usecase.task.create.TaskCreateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class TaskCreateInteractor implements TaskCreateUseCase {

    private final TaskRepository taskRepository;
    private final ConvertEntity convertEntity;
    private final TaskFullSpecification taskFullSpecification;
    @Autowired
    public TaskCreateInteractor(
            TaskRepository taskRepository,
            ConvertEntity convertEntity,
            TaskFullSpecification taskFullSpecification
    ) {
        this.taskRepository = taskRepository;
        this.convertEntity = convertEntity;
        this.taskFullSpecification = taskFullSpecification;
    }
   @Override
   public TaskCreateOutputData handle(TaskCreateInputData inputData) {
       Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
       var task = new TaskEntity(
               new TaskId(uuid),
               new TaskTitle(inputData.getTitle()),
               convertEntity.convertAssignedUserList(inputData.getAssignedUserList())
       );
       if (taskFullSpecification.isSatisfiedBy(task)) {
           taskRepository.insert(task);
       }
       return new TaskCreateOutputData(uuid, inputData.getTitle(), inputData.getAssignedUserList());
   }
}
