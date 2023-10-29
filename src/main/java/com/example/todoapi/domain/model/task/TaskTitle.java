package com.example.todoapi.domain.model.task;

// 値オブジェクト(ルールの集約)
public class TaskTitle {
    private String value;

    public TaskTitle(String value) {
        // ドメインロジック検証
        if (value == null) {
            throw new IllegalArgumentException("タイトルは必須です");
        }
        if (value.length() < 1 || value.length() > 30) {
            throw new IllegalArgumentException("タイトルは1文字以上30文字以下で入力してください");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
