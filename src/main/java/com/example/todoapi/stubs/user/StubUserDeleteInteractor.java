package com.example.todoapi.stubs.user;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.user.delete.UserDeleteInputData;
import com.example.todoapi.usecase.user.delete.UserDeleteOutputData;
import com.example.todoapi.usecase.user.delete.UserDeleteUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class StubUserDeleteInteractor implements UserDeleteUseCase {
	private JsonsLoader jsonsLoader;
	public StubUserDeleteInteractor(JsonsLoader jsonsLoader) {
		this.jsonsLoader = jsonsLoader;
	}

	@Override
	public UserDeleteOutputData handle(UserDeleteInputData inputData) {
		return jsonsLoader.generate(UserDeleteOutputData.class);
	}
}