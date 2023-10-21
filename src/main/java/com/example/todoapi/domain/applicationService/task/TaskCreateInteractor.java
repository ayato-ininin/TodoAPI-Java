package com.example.todoapi.domain.applicationService.task;

import com.example.todoapi.domain.model.task.Task;
import com.example.todoapi.domain.model.task.TaskId;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.domain.model.task.TaskTitle;
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
    @Autowired
    public TaskCreateInteractor(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
   @Override
   public TaskCreateOutputData handle(TaskCreateInputData inputData) {
       Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
       var task = new Task(
               new TaskId(uuid),
               new TaskTitle(inputData.getTitle())
       );
       taskRepository.insert(task);
       return new TaskCreateOutputData(uuid, inputData.getTitle());
   }
}
