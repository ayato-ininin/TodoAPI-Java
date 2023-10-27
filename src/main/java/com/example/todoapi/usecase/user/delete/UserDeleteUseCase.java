package com.example.todoapi.usecase.user.delete;

import com.example.todoapi.usecase.core.UseCase;

public interface UserDeleteUseCase extends UseCase<UserDeleteInputData,UserDeleteOutputData> {
    UserDeleteOutputData handle(UserDeleteInputData inputData);
}