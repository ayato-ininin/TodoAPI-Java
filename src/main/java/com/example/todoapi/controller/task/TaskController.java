package com.example.todoapi.controller.task;

import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.PageDto;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todoapi.model.TaskListDTO;
import com.example.todoapi.usecase.task.create.TaskCreateInputData;
import com.example.todoapi.usecase.task.create.TaskCreateUseCase;
import com.example.todoapi.usecase.task.delete.TaskDeleteInputData;
import com.example.todoapi.usecase.task.delete.TaskDeleteUseCase;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailInputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailUseCase;
import com.example.todoapi.usecase.task.getList.TaskGetListInputData;
import com.example.todoapi.usecase.task.getList.TaskGetListUseCase;
import com.example.todoapi.usecase.task.update.TaskUpdateInputData;
import com.example.todoapi.usecase.task.update.TaskUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController implements TasksApi {

    private final TaskCreateUseCase taskCreateUseCase;
    private final TaskGetListUseCase taskGetListUseCase;
    private final TaskGetDetailUseCase taskGetDetailUseCase;
    private final TaskUpdateUseCase taskUpdateUseCase;
    private final TaskDeleteUseCase taskDeleteUseCase;

    @Autowired
    public TaskController(
            TaskCreateUseCase taskCreateUseCase,
            TaskGetListUseCase taskGetListUseCase,
            TaskGetDetailUseCase taskGetDetailUseCase,
            TaskUpdateUseCase taskUpdateUseCase,
            TaskDeleteUseCase taskDeleteUseCase
    ) {
        this.taskCreateUseCase = taskCreateUseCase;
        this.taskGetListUseCase = taskGetListUseCase;
        this.taskGetDetailUseCase = taskGetDetailUseCase;
        this.taskUpdateUseCase = taskUpdateUseCase;
        this.taskDeleteUseCase = taskDeleteUseCase;
    }

    @Override
    public ResponseEntity<TaskDTO> detail(Long taskId) {
        TaskGetDetailInputData inputData = new TaskGetDetailInputData(taskId);
        var outputData = taskGetDetailUseCase.handle(inputData);
        if (outputData.getTaskData().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var dto = outputData.getTaskData()
                .map(t -> genTaskDto(t.getId(), t.getTitle(), t.getAssignedUserList()))
                .get();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> create(TaskForm taskForm) {
        TaskCreateInputData inputData =
                new TaskCreateInputData(
                        taskForm.getTitle(),
                        taskForm.getAssignedUserList());
        var outputData = taskCreateUseCase.handle(inputData);
        var dto = genTaskDto(outputData.getId(), outputData.getTitle(), outputData.getAssignedUserList());
        return ResponseEntity
                .created(URI.create("/tasks/" + outputData.getId()))
                .body(dto);
    }

    @Override
    public ResponseEntity<TaskListDTO> index(Integer limit, Long offset) {
        TaskGetListInputData inputData = new TaskGetListInputData(limit, offset);
        var outputData = taskGetListUseCase.handle(inputData);
        var dtoList = outputData.getTasks().stream()
                .map(task -> genTaskDto(task.getId(), task.getTitle(), task.getAssignedUserList()))
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
    public ResponseEntity<TaskDTO> update(Long taskId, TaskForm taskForm) {
        TaskUpdateInputData inputData =
                new TaskUpdateInputData(
                        taskId,
                        taskForm.getTitle(),
                        taskForm.getAssignedUserList()
                );
        var outputData = taskUpdateUseCase.handle(inputData);
        var dto = genTaskDto(
                outputData.getTaskData().getId(),
                outputData.getTaskData().getTitle(),
                outputData.getTaskData().getAssignedUserList()
        );
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> delete(Long taskId) {
        TaskDeleteInputData inputData = new TaskDeleteInputData(taskId);
        taskDeleteUseCase.handle(inputData);
        return ResponseEntity.noContent().build();
    }

    private static TaskDTO genTaskDto(long id, String title, List<Long> assignedUserList) {
        var dto = new TaskDTO();
        dto.setId(id);
        dto.setTitle(title);
        dto.setAssignedUserList(assignedUserList);
        return dto;
    }
}
