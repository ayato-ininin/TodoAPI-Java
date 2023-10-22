package com.example.todoapi.controller.advice;

import com.example.todoapi.domain.applicationService.task.common.TaskEntityNotFoundException;
import com.example.todoapi.model.BadRequestError;
import com.example.todoapi.model.InvalidParam;
import com.example.todoapi.model.ResourceNotFoundError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// 下記は、途中でエラーを差し込むためのクラス
// これを定義するだけでエラーをキャッチして、ここからレスポンスを返却できる
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // ↓下記アノテーションで、TaskEntityNotFoundExceptionが発生した場合に、404エラーを返すハンドラー定義
    @ExceptionHandler(TaskEntityNotFoundException.class)
    public ResponseEntity<ResourceNotFoundError> handleTaskEntityNotFoundException(TaskEntityNotFoundException e) {
        var error = new ResourceNotFoundError();
        error.setDetail(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    // POSTメソッドで引数が不正な場合、handleMethodArgumentNotValidが呼ばれるため、
    // ここでエラーをキャッチして、独自のエラーレスポンスを返却する
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var error = BadRequestErrorCreator.from(ex);
        return ResponseEntity.badRequest().body(error);
    }

    // タスク一覧取得APIでlimitやoffsetに不正な値が入っていた場合、
    // ConstraintViolationExceptionの例外になるのでキャッチして、独自のエラーレスポンスを返却する
    @ExceptionHandler(ConstraintViolationException.class)
    public  ResponseEntity<BadRequestError> handleConstraintViolationException(
            ConstraintViolationException ex
    ) {
        var invalidParamList = createInvalidParamList(ex);
        var error = new BadRequestError();
        error.setInvalidParams(invalidParamList);
        return ResponseEntity.badRequest().body(error);
    }

    private static List<InvalidParam> createInvalidParamList(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(CustomExceptionHandler::createInvalidParam)
                .collect(Collectors.toList());
    }

    private static InvalidParam createInvalidParam(ConstraintViolation<?> violation) {
        var parameterOpt = StreamSupport.stream(violation.getPropertyPath().spliterator(),false)
                .filter(node -> node.getKind().equals(ElementKind.PARAMETER))
                .findFirst();
        var invalidParam = new InvalidParam();
        parameterOpt.ifPresent(parameter -> invalidParam.setName(parameter.getName()));
        invalidParam.setReason(violation.getMessage());
        return invalidParam;
    }
}
