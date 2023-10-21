package com.example.todoapi.controller.task;

import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.PageDto;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todoapi.model.TaskListDTO;
import com.example.todoapi.service.task.TaskEntity;
import com.example.todoapi.service.task.TaskService;
import com.example.todoapi.usecase.task.create.TaskCreateInputData;
import com.example.todoapi.usecase.task.create.TaskCreateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
public class TaskController implements TasksApi {

    private final TaskService taskService;
    private final TaskCreateUseCase taskCreateUseCase;

    @Autowired
    public TaskController(
            TaskService taskService,
            TaskCreateUseCase taskCreateUseCase
    ) {
        this.taskService = taskService;
        this.taskCreateUseCase = taskCreateUseCase;
    }

    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {
        var entity = taskService.find(taskId);
        var dto = toTaskDto(entity);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm taskForm) {
        TaskCreateInputData inputData = new TaskCreateInputData(taskForm.getTitle());
        var outputData = taskCreateUseCase.handle(inputData);
        var dto = new TaskDTO();
        dto.setId(outputData.getId());
        dto.setTitle(outputData.getTitle());
        return ResponseEntity
                .created(URI.create("/tasks/" + outputData.getId()))
                .body(dto);
    }

    @Override
    public ResponseEntity<TaskListDTO> listTasks(Integer limit, Long offset) {
        var entityList = taskService.find(limit, offset);
        var dtoList = entityList.stream()
                .map(TaskController::toTaskDto)
                .collect(Collectors.toList());
        var pageDTO = new PageDto();
        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(dtoList.size());

        var dto = new TaskListDTO();
        dto.setPage(pageDTO);
        dto.setResults(dtoList);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> editTask(Long taskId, TaskForm taskForm) {
        var entity = taskService.update(taskId, taskForm.getTitle());
        var dto = toTaskDto(entity);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    // ここで、サービスから受け取ったTaskEntityを出力用のTaskDTOに変換している
    private static TaskDTO toTaskDto(TaskEntity taskEntity) {
        var dto = new TaskDTO();
        dto.setId(taskEntity.getId());
        dto.setTitle(taskEntity.getTitle());
        return dto;
    }
}
