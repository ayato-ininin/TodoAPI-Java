package com.example.todoapi.repository.sample;

import org.springframework.stereotype.Repository;

//↓DIするためのアノテーション(bean登録)
@Repository
public class SampleRepository {

    public SampleRecord select() {
        return new SampleRecord("Hello World!");
    }
}
