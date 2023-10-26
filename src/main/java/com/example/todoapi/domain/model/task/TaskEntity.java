package com.example.todoapi.domain.model.task;

// エンティティ、ドメインオブジェクト
public class TaskEntity {
    // longとLongの違い
    // longはプリミティブ型で、nullを許容しない、64bitの整数型
    // Longはラッパークラスで、nullを許容する、64bitの整数型
    private TaskId id;
    private TaskTitle title;

    public TaskEntity(TaskId id, TaskTitle title) {
        this.id = id;
        this.title = title;
    }

    public TaskId getId() {
        return id;
    }

    public TaskTitle getTitle() {
        return title;
    }

    // nullを許容しないルール
    public void changeTitle(TaskTitle title) {
        if (title == null) {
            throw new IllegalArgumentException("タイトルは必須です");
        }
        this.title = title;
    }
}
