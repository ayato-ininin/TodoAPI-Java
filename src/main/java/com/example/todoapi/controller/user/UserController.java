package com.example.todoapi.controller.user;

import com.example.todoapi.controller.UsersApi;
import com.example.todoapi.model.*;
import com.example.todoapi.usecase.task.update.TaskUpdateInputData;
import com.example.todoapi.usecase.user.create.UserCreateInputData;
import com.example.todoapi.usecase.user.create.UserCreateUseCase;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailInputData;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailUseCase;
import com.example.todoapi.usecase.user.getList.UserGetListInputData;
import com.example.todoapi.usecase.user.getList.UserGetListUseCase;
import com.example.todoapi.usecase.user.update.UserUpdateInputData;
import com.example.todoapi.usecase.user.update.UserUpdateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
public class UserController implements UsersApi {

    private final UserCreateUseCase userCreateUseCase;
    private final UserGetListUseCase userGetListUseCase;
    private final UserGetDetailUseCase userGetDetailUseCase;
    private final UserUpdateUseCase userUpdateUseCase;

    public UserController(
            UserCreateUseCase userCreateUseCase,
            UserGetListUseCase userGetListUseCase,
            UserGetDetailUseCase userGetDetailUseCase,
            UserUpdateUseCase userUpdateUseCase
    ) {
        this.userCreateUseCase = userCreateUseCase;
        this.userGetListUseCase = userGetListUseCase;
        this.userGetDetailUseCase = userGetDetailUseCase;
        this.userUpdateUseCase = userUpdateUseCase;
    }

    @Override
    public ResponseEntity<UserDTO> userDetail(Long taskId) {
        UserGetDetailInputData inputData = new UserGetDetailInputData(taskId);
        var outputData = userGetDetailUseCase.handle(inputData);
        if (outputData.getUserData().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var dto = outputData.getUserData()
                .map(t -> genUserDto(t.getId(), t.getName()))
                .get();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<UserDTO> userCreate(UserForm userForm) {
        UserCreateInputData inputData = new UserCreateInputData(userForm.getName());
        var outputData = userCreateUseCase.handle(inputData);
        var dto = genUserDto(outputData.getId(), outputData.getName());
        return ResponseEntity
                .created(URI.create("/users/" + outputData.getId()))
                .body(dto);
    }

    @Override
    public ResponseEntity<UserListDTO> userIndex(Integer limit, Long offset) {
        UserGetListInputData inputData = new UserGetListInputData(limit, offset);
        var outputData = userGetListUseCase.handle(inputData);
        var dtoList = outputData.getUsers().stream()
                .map(user -> genUserDto(user.getId(), user.getName()))
                .collect(Collectors.toList());
        var pageDTO = new PageDto();
        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(dtoList.size());

        var dto = new UserListDTO();
        dto.setPage(pageDTO);
        dto.setResults(dtoList);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<UserDTO> userUpdate(Long userId, UserForm userForm) {
        UserUpdateInputData inputData = new UserUpdateInputData(userId, userForm.getName());
        var outputData = userUpdateUseCase.handle(inputData);
        var dto = genUserDto(
                outputData.getUserData().getId(),
                outputData.getUserData().getName()
        );
        return ResponseEntity.ok(dto);
    }

    private static UserDTO genUserDto(long id, String name) {
        var dto = new UserDTO();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }
}