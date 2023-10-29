package com.example.todoapi.domain.model.task.specification;

import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.user.UserEntity;
import com.example.todoapi.domain.model.user.UserId;
import com.example.todoapi.domain.model.user.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 複雑な評価処理の集約（集約）
// データベースにアクセスしないといけないルール等
// 評価処理は基本まとめる？
@Service
@Profile("prod")
public class TaskFullSpecification {

    private final UserRepository userRepository;

    public TaskFullSpecification(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Maxサイズの集約（Entityにもかけるが、ロジックをまとめるため）
    // EntityClassの役割を明確にする
    public void isFull(TaskEntity taskEntity) {
        if (taskEntity.getAssignedUserList().size() > 3) {
            throw new IllegalArgumentException("アサインできるユーザは3名までです。");
        }
    }

    public void isValidUser(UserId userId) {
        Optional<UserEntity> userEntity = userRepository.find(userId.getValue());
        if (userEntity.isEmpty()) {
            String message = String.format("ユーザID[%s]が存在しません。", userId.getValue());
            throw new IllegalArgumentException(message);
        }
    }

    // ユーザ数と登録済みユーザかを確認
    public Boolean isSatisfiedBy(TaskEntity taskEntity) {
        if (taskEntity.getAssignedUserList().isEmpty()) {
            return true;
        }
        isFull(taskEntity);
        List<UserId> userIdList = taskEntity.getAssignedUserList();
        for (UserId id : userIdList) {
            isValidUser(id);
        }
        return true;
    }
}
