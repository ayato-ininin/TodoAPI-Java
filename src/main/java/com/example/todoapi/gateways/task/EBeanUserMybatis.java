package com.example.todoapi.gateways.task;

import com.example.todoapi.domain.model.user.UserEntity;
import com.example.todoapi.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

//↓DIするためのアノテーション(bean登録)
//Mapperやから、models/Userのクラスに変換せずアクセスをしているが、
//基本的には、models/Userのクラスに変換してからDBとのやり取りをする
@Mapper
public interface EBeanUserMybatis {

    @Select("SELECT id, name FROM users WHERE id = #{userId}")
    Optional<User> find(long userId);

    @Select("SELECT id, name FROM users LIMIT #{limit} OFFSET #{offset}")
    List<User> findAll(int limit, long offset);

    // optionsで自動採番したIDを取得、セットできる
    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO users (id, name) VALUES (#{id.value}, #{name.value})")
    void insert(UserEntity userEntity);

    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    // 更新後の値は別途取得する必要がある
    @Update("UPDATE users SET name = #{name.value} WHERE id = #{id.value}")
    void update(UserEntity userEntity);

    @Delete("DELETE FROM users WHERE id = #{userId}")
    void delete(Long userId);
}
