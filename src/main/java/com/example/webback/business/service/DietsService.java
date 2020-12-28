package com.example.webback.business.service;

import com.example.webback.business.entity.DietsEntity;
import com.example.webback.web.api.dto.create.DietsCreateDto;
import com.example.webback.web.api.dto.create.WorkoutCreateDto;
import com.example.webback.web.api.dto.read.DietsReadDto;
import com.example.webback.web.api.dto.read.WorkoutReadDto;

import java.util.List;

public interface DietsService {
	DietsReadDto findDtoById(Integer id);

	List<DietsReadDto> findAll();

	void saveDto(DietsCreateDto dto);
}
