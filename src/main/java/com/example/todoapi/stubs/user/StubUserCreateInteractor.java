package com.example.todoapi.stubs.user;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.user.create.UserCreateInputData;
import com.example.todoapi.usecase.user.create.UserCreateOutputData;
import com.example.todoapi.usecase.user.create.UserCreateUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class StubUserCreateInteractor implements UserCreateUseCase {
	private JsonsLoader jsonsLoader;
	public StubUserCreateInteractor(JsonsLoader jsonsLoader) {
		this.jsonsLoader = jsonsLoader;
	}

	@Override
	public UserCreateOutputData handle(UserCreateInputData inputData) {
		return jsonsLoader.generate(UserCreateOutputData.class);
	}
}