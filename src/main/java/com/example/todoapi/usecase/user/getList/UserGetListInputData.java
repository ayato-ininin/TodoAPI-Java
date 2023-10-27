package com.example.todoapi.usecase.user.getList;

import com.example.todoapi.usecase.core.InputData;

public class UserGetListInputData implements InputData<UserGetListOutputData> {
    private Integer limit;
    private Long offset;

    public UserGetListInputData(Integer limit, Long offset) {
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