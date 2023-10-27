package com.example.todoapi.domain.model.user;

// エンティティ、ドメインオブジェクト
public class UserEntity {
    // longとLongの違い
    // longはプリミティブ型で、nullを許容しない、64bitの整数型
    // Longはラッパークラスで、nullを許容する、64bitの整数型
    private UserId id;
    private UserName name;

    public UserEntity(UserId id, UserName name) {
        this.id = id;
        this.name = name;
    }

    public UserId getId() {
        return id;
    }

    public UserName getName() {
        return name;
    }

    // nullを許容しないルール
    public void changeName(UserName name) {
        if (name == null) {
            throw new IllegalArgumentException("氏名は必須です");
        }
        this.name = name;
    }
}
