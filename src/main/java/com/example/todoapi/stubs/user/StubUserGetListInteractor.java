package com.example.todoapi.stubs.user;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.user.getList.UserGetListInputData;
import com.example.todoapi.usecase.user.getList.UserGetListOutputData;
import com.example.todoapi.usecase.user.getList.UserGetListUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class StubUserGetListInteractor implements UserGetListUseCase {
	private JsonsLoader jsonsLoader;
	public StubUserGetListInteractor(JsonsLoader jsonsLoader) {
		this.jsonsLoader = jsonsLoader;
	}

	@Override
	public UserGetListOutputData handle(UserGetListInputData inputData) {
		return jsonsLoader.generate(UserGetListOutputData.class);
	}
}