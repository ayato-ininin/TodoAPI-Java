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
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailInputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailUseCase;
import com.example.todoapi.usecase.task.getList.TaskGetListInputData;
import com.example.todoapi.usecase.task.getList.TaskGetListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
public class TaskController implements TasksApi {

    private final TaskService taskService;
    private final TaskCreateUseCase taskCreateUseCase;
    private final TaskGetListUseCase taskGetListUseCase;
    private final TaskGetDetailUseCase taskGetDetailUseCase;

    @Autowired
    public TaskController(
            TaskService taskService,
            TaskCreateUseCase taskCreateUseCase,
            TaskGetListUseCase taskGetListUseCase,
            TaskGetDetailUseCase taskGetDetailUseCase
    ) {
        this.taskService = taskService;
        this.taskCreateUseCase = taskCreateUseCase;
        this.taskGetListUseCase = taskGetListUseCase;
        this.taskGetDetailUseCase = taskGetDetailUseCase;
    }

    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {
        TaskGetDetailInputData inputData = new TaskGetDetailInputData(taskId);
        var outputData = taskGetDetailUseCase.handle(inputData);
        if (outputData.getTaskData().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var dto = outputData.getTaskData()
                .map(t -> genTaskDto(t.getId(), t.getTitle()))
                .get();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm taskForm) {
        TaskCreateInputData inputData = new TaskCreateInputData(taskForm.getTitle());
        var outputData = taskCreateUseCase.handle(inputData);
        var dto = genTaskDto(outputData.getId(), outputData.getTitle());
        return ResponseEntity
                .created(URI.create("/tasks/" + outputData.getId()))
                .body(dto);
    }

    @Override
    public ResponseEntity<TaskListDTO> listTasks(Integer limit, Long offset) {
        TaskGetListInputData inputData = new TaskGetListInputData(limit, offset);
        var outputData = taskGetListUseCase.handle(inputData);
        var dtoList = outputData.getTasks().stream()
                .map(task -> genTaskDto(task.getId(), task.getTitle()))
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

    private static TaskDTO genTaskDto(long id, String title) {
        var dto = new TaskDTO();
        dto.setId(id);
        dto.setTitle(title);
        return dto;
    }
}
