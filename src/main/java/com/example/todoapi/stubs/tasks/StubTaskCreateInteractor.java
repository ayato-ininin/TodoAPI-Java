package com.example.todoapi.stubs.tasks;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.task.create.TaskCreateInputData;
import com.example.todoapi.usecase.task.create.TaskCreateOutputData;
import com.example.todoapi.usecase.task.create.TaskCreateUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("dev")
public class StubTaskCreateInteractor implements TaskCreateUseCase {
    private JsonsLoader jsonsLoader;
    public StubTaskCreateInteractor(JsonsLoader jsonsLoader) {
        this.jsonsLoader = jsonsLoader;
    }

    @Override
    public TaskCreateOutputData handle(TaskCreateInputData inputData) {
        // 対象のJSONファイルを読み込んで、TaskCreateOutputDataクラスのインスタンスを生成して返す
        return jsonsLoader.generate(TaskCreateOutputData.class);
    }
}
