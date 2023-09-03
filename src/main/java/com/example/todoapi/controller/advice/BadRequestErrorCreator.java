package com.example.todoapi.controller.advice;

import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

public class BadRequestErrorCreator {
    public static BadRequestError from(MethodArgumentNotValidException ex) {
        var invalidParamList = ex.getFieldErrors()
                .stream()
                // .map(fieldError -> createInvalidParam(fieldError))の書き換え(メソッド参照)
                .map(BadRequestErrorCreator::createInvalidParam)
                .collect(Collectors.toList());
        var error = new BadRequestError();
        error.setInvalidParams(invalidParamList);
        return error;
    }

    public static BadRequestError from(ConstraintViolationException ex) {
        return new BadRequestError();
    }

    // 抜き出したい関数の部分を選択して、コマンド + option + Mすると切り出せる。
    private static InvalidParam createInvalidParam(FieldError fieldError) {
        var invalidParam = new InvalidParam();
        invalidParam.setName(fieldError.getField());
        invalidParam.setReason(fieldError.getDefaultMessage());
        return invalidParam;
    }
}
