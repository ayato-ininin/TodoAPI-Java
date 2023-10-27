package com.example.todoapi.stubs.user;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.user.update.UserUpdateInputData;
import com.example.todoapi.usecase.user.update.UserUpdateOutputData;
import com.example.todoapi.usecase.user.update.UserUpdateUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class StubUserUpdateInteractor implements UserUpdateUseCase {
	private JsonsLoader jsonsLoader;
	public StubUserUpdateInteractor(JsonsLoader jsonsLoader) {
		this.jsonsLoader = jsonsLoader;
	}

	@Override
	public UserUpdateOutputData handle(UserUpdateInputData inputData) {
		return jsonsLoader.generate(UserUpdateOutputData.class);
	}
}