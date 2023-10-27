package com.example.todoapi.domain.model.user;

// 値オブジェクト
public class UserName {
    private String value;

    public UserName(String value) {
        // ドメインロジック検証
        if (value == null) {
            throw new IllegalArgumentException("氏名は必須です");
        }
        if (value.length() < 1 || value.length() > 30) {
            throw new IllegalArgumentException("氏名は1文字以上30文字以下で入力してください");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
