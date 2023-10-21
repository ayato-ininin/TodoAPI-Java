package com.example.todoapi.usecase.core;

// ジェネリクスを使用し汎用性を高める
// UseCaseはInputDataを受け取り、OutputDataを返すというインターフェース
public interface UseCase<TInputData extends InputData, TOutputData extends OutputData> {
    TOutputData handle(TInputData inputData);
}
