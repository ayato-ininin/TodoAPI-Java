package com.example.todoapi.domain.model.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> find(long userId);

    List<UserEntity> findAll(int limit, long offset);
    void insert(UserEntity userEntity);

    void update(UserEntity userEntity);

    void delete(Long UserId);
}