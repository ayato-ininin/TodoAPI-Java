package com.example.todoapi.gateways.task;

import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.task.TaskId;
import com.example.todoapi.domain.model.task.TaskRepository;
import com.example.todoapi.domain.model.task.TaskTitle;
import com.example.todoapi.domain.model.user.UserId;
import com.example.todoapi.models.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Task task = convert(taskEntity);
       eBeanTaskMybatis.insert(task);
    }

    public void update(TaskEntity taskEntity) {
        Task task = convert(taskEntity);
       eBeanTaskMybatis.update(task);
    }

    public void delete(Long taskId){
        eBeanTaskMybatis.delete(taskId);
    };

    private TaskEntity convert(Task task) {
        return new TaskEntity(
                new TaskId(task.getId()),
                new TaskTitle(task.getTitle()),
                stringToList(task.getAssignedUserList())
        );
    }

    private Task convert(TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId().getValue(),
                taskEntity.getTitle().getValue(),
                listToString(taskEntity.getAssignedUserList())
        );
    }

    private String listToString(List<UserId> list) {
        StringBuilder str = new StringBuilder();
        for (UserId userId : list) {
            // 最後の要素以外はカンマを付与
            if (list.indexOf(userId) != list.size() - 1) {
                str.append(userId.getValue()).append(",");
            } else {
                str.append(userId.getValue());
            }
        }
        return str.toString();
    }

    private List<UserId> stringToList(String assignedUserList) {
        String[] str = assignedUserList.split(",");
        List<UserId> list = new ArrayList<>();
        for (String s : str) {
            list.add(new UserId(Long.parseLong(s)));
        }
        return list;
    }
}
