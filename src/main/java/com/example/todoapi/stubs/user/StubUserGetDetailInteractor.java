package com.example.todoapi.stubs.user;

import com.example.todoapi.objectLoader.JsonsLoader;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailInputData;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailOutputData;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailUseCase;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class StubUserGetDetailInteractor implements UserGetDetailUseCase {
	private JsonsLoader jsonsLoader;
	public StubUserGetDetailInteractor(JsonsLoader jsonsLoader) {
		this.jsonsLoader = jsonsLoader;
	}

	@Override
	public UserGetDetailOutputData handle(UserGetDetailInputData inputData) {
		return jsonsLoader.generate(UserGetDetailOutputData.class);
	}
}