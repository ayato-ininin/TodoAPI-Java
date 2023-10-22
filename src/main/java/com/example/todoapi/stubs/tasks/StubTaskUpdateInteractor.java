package com.example.todoapi.stubs.tasks;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.task.update.TaskUpdateInputData;
import com.example.todoapi.usecase.task.update.TaskUpdateOutputData;
import com.example.todoapi.usecase.task.update.TaskUpdateUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("dev")
public class StubTaskUpdateInteractor implements TaskUpdateUseCase {

    private JsonsLoader jsonsLoader;
    public StubTaskUpdateInteractor(JsonsLoader jsonsLoader) {
        this.jsonsLoader = jsonsLoader;
    }

    @Override
    public TaskUpdateOutputData handle(TaskUpdateInputData inputData) {
        // 対象のJSONファイルを読み込んで、TaskCreateOutputDataクラスのインスタンスを生成して返す
        return jsonsLoader.generate(TaskUpdateOutputData.class);
    }
}
