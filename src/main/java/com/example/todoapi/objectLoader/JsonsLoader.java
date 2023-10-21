package com.example.todoapi.objectLoader;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsonsLoader {
    // classをキーにしてJsonsFileを保持するMap
    private Map<Class, JsonsFile> files = new HashMap<>();

    public JsonsLoader() {}

    // 対象Classのインスタンスを生成して返す
    public <T> T generate(Class<T> clazz) {
        // Mapにclazzが存在しない場合、createDefaultFilesで空のJsonsFileを生成してMapに追加する
        if(!files.containsKey(clazz)){
            files.put(clazz, createDefaultFiles(clazz));
        }

        JsonsFile<T> jsonsFile = files.get(clazz);
        return jsonsFile.next();
    }

    // Jsonsファイルが対象ディレクトリ配下にあれば、パスと共にJsonsFileを生成する
    // なければ空？
    private <T> JsonsFile<T> createDefaultFiles(Class<T> clazz) {
        String fileDirectoryPath = "/Users/ayatoymauchi/java/todo-api/src/main/java/com/example/todoapi/stubs/jsons";
        String filePath = new File(fileDirectoryPath, clazz.getSimpleName() + ".jsons").getPath();
        return new JsonsFile<>(clazz, filePath);
    }
}
