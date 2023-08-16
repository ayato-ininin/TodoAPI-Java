package com.example.todoapi.service.sample;

import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
public class SampleService {

    public SampleEntity find() {
        return new SampleEntity("Hello World!");
    }
}
