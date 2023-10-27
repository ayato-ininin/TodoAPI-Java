package com.example.todoapi.usecase.user.getDetail;

import com.example.todoapi.usecase.core.UseCase;

public interface UserGetDetailUseCase extends UseCase<UserGetDetailInputData,UserGetDetailOutputData> {
    UserGetDetailOutputData handle(UserGetDetailInputData inputData);
}