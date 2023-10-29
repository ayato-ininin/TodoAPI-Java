package com.example.todoapi.gateways.user;

import com.example.todoapi.domain.model.user.UserEntity;
import com.example.todoapi.domain.model.user.UserId;
import com.example.todoapi.domain.model.user.UserName;
import com.example.todoapi.domain.model.user.UserRepository;
import com.example.todoapi.models.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class EBeanUserRepository implements UserRepository {
    private final EBeanUserMybatis eBeanUserMybatis;

    public EBeanUserRepository(EBeanUserMybatis eBeanUserMybatis) {
        this.eBeanUserMybatis = eBeanUserMybatis;
    }

    public Optional<UserEntity> find(long userId) {
        Optional<User> user = eBeanUserMybatis.find(userId);

        if(user.isPresent()) {
            UserEntity userEntity = convert(user.get());
            return Optional.of(userEntity);
        }else{
            return Optional.empty();
        }
    }

    public List<UserEntity> findAll(int limit, long offset) {
        List<User> users = eBeanUserMybatis.findAll(limit, offset);
        return users.stream()
                .map(this::convert)
                .toList();
    }

    public void insert(UserEntity userEntity) {
       eBeanUserMybatis.insert(userEntity);
    }

    public void update(UserEntity userEntity) {
       eBeanUserMybatis.update(userEntity);
    }

    public void delete(Long userId){
        eBeanUserMybatis.delete(userId);
    };

    private UserEntity convert(User user) {
        return new UserEntity(
                new UserId(user.getId()),
                new UserName(user.getName())
        );
    }
}
