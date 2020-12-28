package com.example.webback.business.service;

import com.example.webback.web.api.dto.create.WorkoutCreateDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadSmallDto;

import java.util.List;

public interface WorkoutsService {
    WorkoutReadDto findDtoById(Integer id);

    List<WorkoutReadSmallDto> findAll();

    void saveDto(WorkoutCreateDto dto);
}
