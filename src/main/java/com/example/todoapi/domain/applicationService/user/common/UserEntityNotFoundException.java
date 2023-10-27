package com.example.todoapi.domain.applicationService.user.common;

public class UserEntityNotFoundException extends RuntimeException{

    // 取得に失敗したidを保持する
    private long userId;

    public UserEntityNotFoundException(long userId) {
        // super()は親クラスのコンストラクタを呼び出す
        super("UserEntity id not found. [userId=" + userId + "]");
        this.userId = userId;
    }
}
