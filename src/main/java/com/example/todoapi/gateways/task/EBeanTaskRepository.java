package com.example.todoapi.gateways.task;

import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.models.Task;
import com.example.todoapi.repository.task.TaskRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Optional;

//↓DIするためのアノテーション(bean登録)
//Mapperやから、models/Taskのクラスに変換せずアクセスをしているが、
//基本的には、models/Taskのクラスに変換してからDBとのやり取りをする
@Mapper
@Profile("prod")
public interface EBeanTaskRepository extends TaskRepository {

    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(long taskId);

    @Select("SELECT id, title FROM tasks LIMIT #{limit} OFFSET #{offset}")
    List<Task> selectList(int limit, long offset);

    // optionsで自動採番したIDを取得、セットできる
    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title.value})")
    void insert(TaskEntity taskEntity);

    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    // 更新後の値は別途取得する必要がある
    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id}")
    void update(TaskRecord taskRecord);

    @Delete("DELETE FROM tasks WHERE id = #{taskId}")
    void delete(Long taskId);
}
