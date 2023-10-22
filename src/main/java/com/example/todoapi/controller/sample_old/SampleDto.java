package com.example.todoapi.controller.sample_old;

import java.time.LocalDateTime;

// レスポンス用のDTO
public class SampleDto {

    private String content;
    private LocalDateTime timestamp;

    // コンストラクタ
    public SampleDto(String content, LocalDateTime timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    // getter
    public String getContent() {
        return content;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
