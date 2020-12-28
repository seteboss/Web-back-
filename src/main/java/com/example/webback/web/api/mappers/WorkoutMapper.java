package com.example.webback.web.api.mappers;

import com.example.webback.business.entity.WorkoutsEntity;
import com.example.webback.web.api.dto.create.WorkoutCreateDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadSmallDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class WorkoutMapper {

    public abstract WorkoutReadDto toReadDto (WorkoutsEntity entity);

    public abstract List<WorkoutReadDto> toReadDtos (List<WorkoutsEntity> entities);

    public abstract List<WorkoutReadSmallDto> toReadSmallDtos (List<WorkoutsEntity> entities);

    public abstract WorkoutsEntity toEntity (WorkoutCreateDto dto);
}
