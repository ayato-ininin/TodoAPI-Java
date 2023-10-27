package com.example.todoapi.controller.user;

import com.example.todoapi.controller.UsersApi;
import com.example.todoapi.model.UserDTO;
import com.example.todoapi.model.UserForm;
import com.example.todoapi.usecase.user.create.UserCreateInputData;
import com.example.todoapi.usecase.user.create.UserCreateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController implements UsersApi {

    private final UserCreateUseCase userCreateUseCase;

    public UserController(UserCreateUseCase userCreateUseCase) {
        this.userCreateUseCase = userCreateUseCase;
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

    private static UserDTO genUserDto(long id, String name) {
        var dto = new UserDTO();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }
}