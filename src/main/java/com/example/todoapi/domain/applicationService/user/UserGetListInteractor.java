package com.example.todoapi.domain.applicationService.user;

import com.example.todoapi.domain.model.user.UserEntity;
import com.example.todoapi.domain.model.user.UserRepository;
import com.example.todoapi.usecase.user.common.UserData;
import com.example.todoapi.usecase.user.getList.UserGetListInputData;
import com.example.todoapi.usecase.user.getList.UserGetListOutputData;
import com.example.todoapi.usecase.user.getList.UserGetListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("prod")
public class UserGetListInteractor implements UserGetListUseCase {

	private final UserRepository userRepository;
	@Autowired
	public UserGetListInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserGetListOutputData handle(UserGetListInputData inputData) {
		List<UserEntity> users = userRepository.findAll(inputData.getLimit(), inputData.getOffset());
		List<UserData> userData = users.stream()
				.map(t -> new UserData(
						t.getId().getValue(),
						t.getName().getValue())
				).collect(Collectors.toList());
		return new UserGetListOutputData(userData);
	}
}