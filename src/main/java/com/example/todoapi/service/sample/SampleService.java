package com.example.todoapi.service.sample;

import com.example.todoapi.repository.sample.SampleRepository;
import org.springframework.stereotype.Service;

//↓DIするためのアノテーション(bean登録)
@Service
public class SampleService {

    private final SampleRepository repository;
    public SampleService(SampleRepository repository) {
        this.repository = repository;
    }

    public SampleEntity find() {
        var record = repository.select();
        return new SampleEntity(record.getContent()) ;
    }
}
