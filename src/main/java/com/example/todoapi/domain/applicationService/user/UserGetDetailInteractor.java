package com.example.todoapi.domain.applicationService.user;

import com.example.todoapi.domain.model.user.UserEntity;
import com.example.todoapi.domain.model.user.UserRepository;
import com.example.todoapi.usecase.user.common.UserData;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailInputData;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailOutputData;
import com.example.todoapi.usecase.user.getDetail.UserGetDetailUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("prod")
public class UserGetDetailInteractor implements UserGetDetailUseCase {

	private final UserRepository userRepository;
	@Autowired
	public UserGetDetailInteractor(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@Override
	public UserGetDetailOutputData handle(UserGetDetailInputData inputData) {
		Optional<UserEntity> userEntity = userRepository.find(inputData.getId());
		Optional<UserData> userData = userEntity
				.map(t -> new UserData(
						t.getId().getValue(),
						t.getName().getValue())
				);
		return new UserGetDetailOutputData(userData);
	}
}