package com.example.webback.business.service;

import com.example.webback.business.entity.AuthorityEntity;
import com.example.webback.business.entity.UserEntity;
import com.example.webback.business.enums.AuthorityEnum;
import com.example.webback.web.api.dto.create.CreateUserDto;
import com.example.webback.web.api.dto.read.DietsReadDto;
import com.example.webback.web.api.dto.read.PointReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;
import com.example.webback.web.api.dto.update.ProgressDto;
import com.example.webback.web.api.dto.update.UserUpdateDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService extends UserDetailsService {
	UserEntity getById(UUID id);

	UserEntity findByEmail(String email);

	UUID register(CreateUserDto newUser);

	UserEntity saveForRegister(UserEntity user);

	UserEntity save(UserEntity user);

	AuthorityEntity findAuthorityByName(AuthorityEnum authority);

	void update(UserUpdateDto userDto);

	Map<String, Object> getAdditionInformation(OAuth2Authentication auth);

	void checkResolution(UUID id);

	Long getUserCount();

	DietsReadDto getDiet(UUID id);

	WorkoutReadDto getWorkout(UUID id);

	void updateProgress(ProgressDto dto);

	List<PointReadDto> getPoints(UUID id);

}
