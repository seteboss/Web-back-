package com.example.webback.business.service.impl;

import com.example.webback.business.dao.AuthorityRepository;
import com.example.webback.business.dao.UserRepository;
import com.example.webback.business.entity.AuthorityEntity;
import com.example.webback.business.entity.GraghPointEntity;
import com.example.webback.business.entity.UserEntity;
import com.example.webback.business.enums.AuthorityEnum;
import com.example.webback.business.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.webback.web.api.dto.create.CreateUserDto;
import com.example.webback.web.api.dto.read.DietsReadDto;
import com.example.webback.web.api.dto.read.PointReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;
import com.example.webback.web.api.dto.update.ProgressDto;
import com.example.webback.web.api.dto.update.UserUpdateDto;
import com.example.webback.web.api.error.NotAccessException;
import com.example.webback.web.api.error.ResourceNotFoundException;
import com.example.webback.web.api.mappers.DietsMapper;
import com.example.webback.web.api.mappers.UserMapper;
import com.example.webback.web.api.mappers.WorkoutMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;
    private final DietsMapper dietsMapper;
    private final WorkoutMapper workoutMapper;

    private static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    @Override
    public UserEntity getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public UserEntity loadUserByUsername(String userName) throws UsernameNotFoundException {
        return findByEmail(userName);
    }

    @Override
    public UserEntity findByEmail(String email) {
        log.info("Find user by email: {}", email);
        return userRepository.findByUsername(email).orElseThrow(() -> new ResourceNotFoundException("User", email));
    }


    @Override
    public UserEntity saveForRegister(UserEntity user) {
        log.info("Save user: {}",user);
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }


    @Override
    public UUID register(CreateUserDto newUser) {
        log.info("register service start new user: {}", newUser);
        UserEntity user = userMapper.toNewEntity(newUser);
        user.addAuthority(findAuthorityByName(AuthorityEnum.USER));
        log.info("register service end");
        return saveForRegister(user).getId();

    }

    @Override
    public AuthorityEntity findAuthorityByName(AuthorityEnum authority) {
        log.info("Find authority by name:{}", authority.name());
        return authorityRepository.findAllByName(authority.name());
    }

    @Override
    public void update(UserUpdateDto userDto){
        UUID id = userDto.getId();
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));;
        userMapper.fromUpdateDto(userEntity, userDto);
        userRepository.save(userEntity);


    }

    @Override
    @SneakyThrows
    public Map<String, Object> getAdditionInformation(OAuth2Authentication auth) {
        var details = (OAuth2AuthenticationDetails) auth.getDetails();
        return (Map<String, Object>) details.getDecodedDetails();
    }

    @Override
    public void checkResolution(UUID id) {
        if (!isAccess(id))
            throw new NotAccessException("Нет доступа");

    }

    @Override
    public Long getUserCount() {
        return userRepository.count();
    }

    @Override
    public DietsReadDto getDiet(UUID id) {
        UserEntity entity = getById(id);
        return dietsMapper.toReadDto(entity.getDiets());
    }

    @Override
    public WorkoutReadDto getWorkout(UUID id) {
        UserEntity entity = getById(id);
        return workoutMapper.toReadDto(entity.getWorkouts());
    }

    @Override
    @Transactional
    public void updateProgress(ProgressDto dto) {
        UserEntity entity = getById(dto.getId());
        GraghPointEntity graghPointEntity = new GraghPointEntity();
        graghPointEntity.setUser(entity);
        graghPointEntity.setDate(dto.getDate());
        graghPointEntity.setValue(dto.getValue());
        entity.getPointEntities().add(graghPointEntity);
    }

    @Override
    public List<PointReadDto> getPoints(UUID id) {
        UserEntity entity = getById(id);
        List<PointReadDto> listDto = new ArrayList<>();
        List<GraghPointEntity> listEntity = entity.getPointEntities();
        for (int i = listEntity.size(), j = 0; i > 0 && j < 5 ; i--, j++) {
            GraghPointEntity graghPointEntity = listEntity.get(j);
            PointReadDto readDto = new PointReadDto();
            readDto.setDate(graghPointEntity.getDate());
            readDto.setValue(graghPointEntity.getValue());
            listDto.add(readDto);
        }
        return listDto;
    }

    private boolean isAccess(UUID id) {
        String userId = getAdditionInformation(getOAuth2Authentication()).get("user_id").toString();
        return id.toString().equals(userId);
    }

    private OAuth2Authentication getOAuth2Authentication() {
        return (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
    }






}
