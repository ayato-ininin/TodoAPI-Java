package com.example.todoapi.controller.task;

import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.service.task.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController implements TasksApi {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {
        var entity = taskService.find(taskId);
        var dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return ResponseEntity.ok(dto);
    }
}
