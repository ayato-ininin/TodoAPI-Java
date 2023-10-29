package com.example.todoapi.domain.applicationService.task.common;

import com.example.todoapi.domain.model.task.TaskEntity;
import com.example.todoapi.domain.model.user.UserId;
import com.example.todoapi.usecase.task.common.TaskData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvertEntity {

    public List<UserId> convertAssignedUserList(List<Long> assignedUserList) {
        return assignedUserList.stream()
                .map(UserId::new)
                .toList();
    }

    public List<Long> convertLongAssignedUserList(List<UserId> assignedUserList) {
        return assignedUserList.stream()
                .map(UserId::getValue)
                .toList();
    }

    public TaskData convertTaskData(TaskEntity taskEntity) {
        return new TaskData(
                taskEntity.getId().getValue(),
                taskEntity.getTitle().getValue(),
                convertLongAssignedUserList(taskEntity.getAssignedUserList())
        );
    }

}
