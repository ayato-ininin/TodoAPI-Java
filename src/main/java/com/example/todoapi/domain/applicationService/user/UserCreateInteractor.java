package com.example.todoapi.domain.applicationService.user;

import com.example.todoapi.domain.model.user.UserEntity;
import com.example.todoapi.domain.model.user.UserId;
import com.example.todoapi.domain.model.user.UserName;
import com.example.todoapi.domain.model.user.UserRepository;
import com.example.todoapi.usecase.user.create.UserCreateInputData;
import com.example.todoapi.usecase.user.create.UserCreateOutputData;
import com.example.todoapi.usecase.user.create.UserCreateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Profile("prod")
public class UserCreateInteractor implements UserCreateUseCase {

	private final UserRepository userRepository;
	@Autowired
	public UserCreateInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserCreateOutputData handle(UserCreateInputData inputData) {
		Long uuid = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		var task = new UserEntity(
				new UserId(uuid),
				new UserName(inputData.getName())
		);
		userRepository.insert(task);
		return new UserCreateOutputData(uuid, inputData.getName());
	}
}