package com.example.todoapi.domain.applicationService.user;

import com.example.todoapi.domain.applicationService.user.common.UserEntityNotFoundException;
import com.example.todoapi.domain.model.user.UserRepository;
import com.example.todoapi.usecase.user.delete.UserDeleteInputData;
import com.example.todoapi.usecase.user.delete.UserDeleteOutputData;
import com.example.todoapi.usecase.user.delete.UserDeleteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class UserDeleteInteractor implements UserDeleteUseCase {

	private final UserRepository userRepository;
	@Autowired
	public UserDeleteInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserDeleteOutputData handle(UserDeleteInputData inputData) {
		userRepository.find(inputData.getId())
				.orElseThrow(() -> new UserEntityNotFoundException(inputData.getId()));
		userRepository.delete(inputData.getId());
		return new UserDeleteOutputData();
	}
}