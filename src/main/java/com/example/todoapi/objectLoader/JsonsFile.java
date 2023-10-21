package com.example.todoapi.objectLoader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonsFile<T> {
    private final Class<T> clazz;
    private final Path filePath;
    private int current;

    public JsonsFile(Class<T> clazz, String filePath){
        this.clazz = clazz;
        this.filePath = Paths.get(filePath);
    }


    class Test {
        public Optional<String> data;
    }
    // 対象ClassのJavaオブジェクトをJSONまたはデフォルト値を利用して返す関数
    public T next() {
        // ファイルが存在しない場合、デフォルト値を持ったJavaオブジェクトを生成する
        if (!Files.exists(filePath)) {
            return createAnyInstance();
        }

        String content;
        try {
            content = Files.readAllLines(filePath).stream().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
            return createAnyInstance();
        }

        // 複数のJSONがあれば、currentはこのインスタンスがずっと引き継がれるので、
        // 3つJSONがあれば、3回分違うものを返却ができる。毎回同じインスタンスではなく。
        // 複数のJSONがあれば、それを一回で全部返すのではない。出力するjsonを変える役目
        List<String> jsons = splitJsons(content);
        String targetJson = current < jsons.size() ? jsons.get(current) : lastOrDefault(jsons);
        current++;

        if (targetJson == null) {
            return createAnyInstance();
        }

        ObjectMapper mapper = new ObjectMapper();//Jacksonを使用してJSONをJavaオブジェクトに変換（デシリアライズ）
        //JacksonがJava 8の特定の型、特にOptionalなどを正しく処理できるようにするためのモジュール追加
        mapper.registerModule(new Jdk8Module());
        try {
            return mapper.readValue(targetJson, clazz);//デシリアライズ処理
        } catch (IOException e) {
            e.printStackTrace();
            return createAnyInstance();
        }
    }

    // Classのコンストラクタを呼び出して、デフォルト値を持ったJavaオブジェクトを生成する関数
    // x.getName()で型名を取得して、switch文で型ごとのデフォルト値を返す
    private T createAnyInstance() {
        Constructor constructor = clazz.getConstructors()[0];
        try {
            // パラメータの配列から、デフォルト値の配列を作って引数とする
            Object[] args = Arrays.stream(constructor.getParameterTypes())
                    .map(x -> {
                        switch (x.getName()){
                            case "java.util.List": return new ArrayList();
                            case "java.lang.String": return "";
                            case "long": return 0L;
                            default: return null;
                        }

                    })
                    .toArray();
            return (T)constructor.newInstance(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 複数のJSONがあれば、それを配列にして分割する関数
    // 例) {"id": 1, "name": "hoge"}{"id": 2, "name": "fuga"}{"id": 3, "name": "piyo"}
    // これを一つのcontent文字列から、３つのaccに分割する
    // ※nestによる管理をすることでネストが深くなった場合も区別することができる
    private List<String> splitJsons(String content) {
        List<String> acc = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Integer nest = 0;

        for(Character c : content.toCharArray()) {
            sb.append(c);
            if(c.equals('{')) {
                nest++;
            }else if(c.equals('}')){
                nest--;
                if(nest == 0) {
                    acc.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }

        return acc;
    }

    // リストの最後のJSONを返す関数
    // 3つJSONがあっても4回目はもうないのでそれ以降はずっと3つ目を返せるように
    private <T> T lastOrDefault(List<T> source){
        if(source == null) {
            return null;
        }

        if(source.size() == 0) {
            return null;
        }

        return source.get(source.size() - 1);
    }
}
