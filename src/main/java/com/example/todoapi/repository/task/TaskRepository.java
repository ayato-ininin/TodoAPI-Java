package com.example.todoapi.repository.task;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TaskRepository {

    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(long taskId);

    @Select("SELECT id, title FROM tasks LIMIT #{limit} OFFSET #{offset}")
    List<TaskRecord> selectList(int limit, long offset);

    // optionsで自動採番したIDを取得、セットできる
    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})")
    void insert(TaskRecord record);

    // mybatisの制限で更新後の値を取得できないので、voidになってしまう
    // 更新後の値は別途取得する必要がある
    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id}")
    void update(TaskRecord taskRecord);
}
