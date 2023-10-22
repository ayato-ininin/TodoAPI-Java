package com.example.todoapi.gateways.task;

import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskId;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.domain.model.task.TaskTitle;
import com.example.todoapi.models.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//↓DIするためのアノテーション(bean登録)
@Service
@Profile("prod")
public class EBeanTaskRepository implements TaskRepository {
    private final EBeanTaskMybatis eBeanTaskMybatis;

    public EBeanTaskRepository(EBeanTaskMybatis eBeanTaskMybatis) {
        this.eBeanTaskMybatis = eBeanTaskMybatis;
    }

    public Optional<TaskEntity> find(long taskId) {
        Optional<Task> task = eBeanTaskMybatis.find(taskId);

        if(task.isPresent()) {
            TaskEntity taskEntity = convert(task.get());
            return Optional.of(taskEntity);
        }else{
            return Optional.empty();
        }
    }

    public List<TaskEntity> findAll(int limit, long offset) {
        List<Task> tasks = eBeanTaskMybatis.findAll(limit, offset);
        return tasks.stream()
                .map(this::convert)
                .toList();
    }

    public void insert(TaskEntity taskEntity) {
       eBeanTaskMybatis.insert(taskEntity);
    }

    public void update(TaskEntity taskEntity) {
       eBeanTaskMybatis.update(taskEntity);
    }

    public void delete(Long taskId){};

    private TaskEntity convert(Task task) {
        return new TaskEntity(
                new TaskId(task.getId()),
                new TaskTitle(task.getTitle())
        );
    }
}
