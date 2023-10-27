package com.example.todoapi.domain.applicationService.user;

import com.example.todoapi.domain.applicationService.user.common.UserEntityNotFoundException;
import com.example.todoapi.domain.model.user.UserEntity;
import com.example.todoapi.domain.model.user.UserName;
import com.example.todoapi.domain.model.user.UserRepository;
import com.example.todoapi.usecase.user.common.UserData;
import com.example.todoapi.usecase.user.update.UserUpdateInputData;
import com.example.todoapi.usecase.user.update.UserUpdateOutputData;
import com.example.todoapi.usecase.user.update.UserUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class UserUpdateInteractor implements UserUpdateUseCase {

	private final UserRepository userRepository;
	@Autowired
	public UserUpdateInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserUpdateOutputData handle(UserUpdateInputData inputData) {
		UserEntity userEntity = userRepository.find(inputData.getId())
				.orElseThrow(() -> new UserEntityNotFoundException(inputData.getId()));
		UserName name = new UserName(inputData.getName());
		userEntity.changeName(name);
		userRepository.update(userEntity);
		UserData userData = new UserData(
				inputData.getId(),
				name.getValue()
		);
		return new UserUpdateOutputData(userData);
	}
}