package com.example.todoapi.domain.model.task;

// エンティティ、ドメインオブジェクト
public class Task {
    // longとLongの違い
    // longはプリミティブ型で、nullを許容しない、64bitの整数型
    // Longはラッパークラスで、nullを許容する、64bitの整数型
    private TaskId id;
    private TaskTitle title;

    public Task(TaskId id, TaskTitle title) {
        this.id = id;
        this.title = title;
    }

    public TaskId getId() {
        return id;
    }

    public TaskTitle getTitle() {
        return title;
    }
}
