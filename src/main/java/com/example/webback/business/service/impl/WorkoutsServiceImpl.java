package com.example.webback.business.service.impl;

import com.example.webback.business.dao.WorkoutsRepository;
import com.example.webback.business.service.WorkoutsService;
import com.example.webback.web.api.dto.create.WorkoutCreateDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadSmallDto;
import com.example.webback.web.api.error.ResourceNotFoundException;
import com.example.webback.web.api.mappers.WorkoutMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutsServiceImpl implements WorkoutsService {

	private final WorkoutsRepository repository;
	private final WorkoutMapper mapper;

    public WorkoutsServiceImpl(WorkoutsRepository repository, WorkoutMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public WorkoutReadDto findDtoById(Integer id) {
        return mapper.toReadDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Override
    public List<WorkoutReadSmallDto> findAll() {
        return mapper.toReadSmallDtos(repository.findAll());
    }



    @Override
    public void saveDto(WorkoutCreateDto dto) {
        repository.save(mapper.toEntity(dto));
    }
}
