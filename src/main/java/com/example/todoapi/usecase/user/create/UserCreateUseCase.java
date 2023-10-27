package com.example.todoapi.usecase.user.create;

import com.example.todoapi.usecase.core.UseCase;

public interface UserCreateUseCase extends UseCase<UserCreateInputData,UserCreateOutputData> {
    UserCreateOutputData handle(UserCreateInputData inputData);
}