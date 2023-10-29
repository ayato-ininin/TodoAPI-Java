package com.example.todoapi.domain.model.task;

import com.example.todoapi.domain.model.user.UserId;

import java.util.ArrayList;
import java.util.List;

// エンティティ、ドメインオブジェクト
public class TaskEntity {
    // longとLongの違い
    // longはプリミティブ型で、nullを許容しない、64bitの整数型
    // Longはラッパークラスで、nullを許容する、64bitの整数型
    private TaskId id;
    private TaskTitle title;
    private List<UserId> assignedUserList;

    public TaskEntity(TaskId id, TaskTitle title, List<UserId> assignedUserList) {
        this.id = id;
        this.title = title;
        this.assignedUserList = assignedUserList;
    }

    public TaskId getId() {
        return id;
    }

    public TaskTitle getTitle() {
        return title;
    }

    public List<UserId> getAssignedUserList() {
        return new ArrayList<>(assignedUserList); // Return a copy to ensure immutability
    }

    // nullを許容しないルール
    // ルールの集約
    public void changeTitle(TaskTitle title) {
        if (title == null) {
            throw new IllegalArgumentException("タイトルは必須です");
        }
        this.title = title;
    }

    // ドメインロジックはSpecificationに記述
    public void changeAssignedUserList(List<UserId> assignedUserList) {
        this.assignedUserList = new ArrayList<>(assignedUserList); // Return a copy to ensure immutability
    }
}
