package com.example.todoapi.usecase.task.getList;

import com.example.todoapi.usecase.core.InputData;

public class TaskGetListInputData implements InputData<TaskGetListOutputData> {
    private Integer limit;
    private Long offset;

    public TaskGetListInputData(Integer limit, Long offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public Long getOffset() {
        return offset;
    }
}
