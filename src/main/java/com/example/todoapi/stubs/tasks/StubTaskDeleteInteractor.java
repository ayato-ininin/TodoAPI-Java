package com.example.todoapi.stubs.tasks;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.task.delete.TaskDeleteInputData;
import com.example.todoapi.usecase.task.delete.TaskDeleteOutputData;
import com.example.todoapi.usecase.task.delete.TaskDeleteUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("dev")
public class StubTaskDeleteInteractor implements TaskDeleteUseCase {

    private JsonsLoader jsonsLoader;
    public StubTaskDeleteInteractor(JsonsLoader jsonsLoader) {
        this.jsonsLoader = jsonsLoader;
    }

    @Override
    public TaskDeleteOutputData handle(TaskDeleteInputData inputData) {
        // 対象のJSONファイルを読み込んで、TaskCreateOutputDataクラスのインスタンスを生成して返す
        return jsonsLoader.generate(TaskDeleteOutputData.class);
    }
}
