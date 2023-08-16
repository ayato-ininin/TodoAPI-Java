package com.example.todoapi.controller.advice;

import com.example.todoapi.model.ResourceNotFoundError;
import com.example.todoapi.service.task.TaskEntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 下記は、途中でエラーを差し込むためのクラス
// これを定義するだけでエラーをキャッチして、ここからレスポンスを返却できる
@RestControllerAdvice
public class CustomExceptionHandler {

    // ↓下記アノテーションで、TaskEntityNotFoundExceptionが発生した場合に、404エラーを返すハンドラー定義
    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<ResourceNotFoundError> handleTaskEntityNotFoundException(TaskEntityNotFoundException e) {
        var error = new ResourceNotFoundError();
        error.setDetail(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}
