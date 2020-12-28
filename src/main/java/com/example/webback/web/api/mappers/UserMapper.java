package com.example.webback.web.api.mappers;


import com.example.webback.business.entity.UserEntity;
import com.example.webback.web.api.dto.create.CreateUserDto;
import com.example.webback.web.api.dto.update.UserUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class UserMapper {

    public UserEntity toNewEntity(CreateUserDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername( dto.getUsername() );
        userEntity.setFirstName(dto.getFirstName());
        userEntity.setSecondName(dto.getSecondName());
        userEntity.setEnabled(true);
        userEntity.setPassword( dto.getPassword() );
        userEntity.setAccountConfirmed(true);

        return userEntity;
    }

    @Mapping(target = "id", ignore = true)
    public abstract void fromUpdateDto(@MappingTarget UserEntity userEntity, UserUpdateDto dto);

}
