package com.example.todoapi.stubs.tasks;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.task.getList.TaskGetListInputData;
import com.example.todoapi.usecase.task.getList.TaskGetListOutputData;
import com.example.todoapi.usecase.task.getList.TaskGetListUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("dev")
public class StubTaskGetListInteractor implements TaskGetListUseCase {

    private JsonsLoader jsonsLoader;
    public StubTaskGetListInteractor(JsonsLoader jsonsLoader) {
        this.jsonsLoader = jsonsLoader;
    }

    @Override
    public TaskGetListOutputData handle(TaskGetListInputData inputData) {
        // 対象のJSONファイルを読み込んで、TaskCreateOutputDataクラスのインスタンスを生成して返す
        return jsonsLoader.generate(TaskGetListOutputData.class);
    }
}
