package com.example.todoapi.repository.task;

public class TaskRecord {

    // longとLongの違い
    // longはプリミティブ型で、nullを許容しない、64bitの整数型
    // Longはラッパークラスで、nullを許容する、64bitの整数型
    private Long id;
    private String title;

    public TaskRecord(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
