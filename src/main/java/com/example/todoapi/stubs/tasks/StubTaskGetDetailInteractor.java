package com.example.todoapi.stubs.tasks;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailInputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailOutputData;
import com.example.todoapi.usecase.task.getDetail.TaskGetDetailUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("dev")
public class StubTaskGetDetailInteractor implements TaskGetDetailUseCase {

    private JsonsLoader jsonsLoader;
    public StubTaskGetDetailInteractor(JsonsLoader jsonsLoader) {
        this.jsonsLoader = jsonsLoader;
    }

    @Override
    public TaskGetDetailOutputData handle(TaskGetDetailInputData inputData) {
        // 対象のJSONファイルを読み込んで、TaskCreateOutputDataクラスのインスタンスを生成して返す
        return jsonsLoader.generate(TaskGetDetailOutputData.class);
    }
}
