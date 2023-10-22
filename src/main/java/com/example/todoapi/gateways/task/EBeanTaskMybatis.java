package com.example.todoapi.gateways.task;

import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.models.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

//↓DIするためのアノテーション(bean登録)
//Mapperやから、models/Taskのクラスに変換せずアクセスをしているが、
//基本的には、models/Taskのクラスに変換してからDBとのやり取りをする
@Mapper
public interface EBeanTaskMybatis {

    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<Task> find(long taskId);

    @Select("SELECT id, title FROM tasks LIMIT #{limit} OFFSET #{offset}")
    List<Task> findAll(int limit, long offset);

    // optionsで自動採番したIDを取得、セットできる
    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (id, title) VALUES (#{id.value}, #{title.value})")
    void insert(TaskEntity taskEntity);

    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    // 更新後の値は別途取得する必要がある
    @Update("UPDATE tasks SET title = #{title.value} WHERE id = #{id.value}")
    void update(TaskEntity taskEntity);

    @Delete("DELETE FROM tasks WHERE id = #{taskId}")
    void delete(Long taskId);
}
