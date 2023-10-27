package com.example.todoapi.domain.model.user;

// 値オブジェクト
public class UserId {
    private long value;

    public UserId(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
