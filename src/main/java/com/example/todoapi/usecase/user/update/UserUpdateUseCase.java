package com.example.todoapi.usecase.user.update;

import com.example.todoapi.usecase.core.UseCase;

public interface UserUpdateUseCase extends UseCase<UserUpdateInputData,UserUpdateOutputData> {
    UserUpdateOutputData handle(UserUpdateInputData inputData);
}