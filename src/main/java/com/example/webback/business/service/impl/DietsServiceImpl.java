package com.example.webback.business.service.impl;

import com.example.webback.business.dao.DietsRepository;
import com.example.webback.business.service.DietsService;

import java.util.List;

import com.example.webback.web.api.dto.create.DietsCreateDto;
import com.example.webback.web.api.dto.read.DietsReadDto;
import com.example.webback.web.api.error.ResourceNotFoundException;
import com.example.webback.web.api.mappers.DietsMapper;
import org.springframework.stereotype.Service;

@Service
public class DietsServiceImpl implements DietsService {

	private final DietsRepository repository;
	private final DietsMapper mapper;

	public DietsServiceImpl(DietsRepository repository, DietsMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}


	@Override
	public DietsReadDto findDtoById(Integer id) {
		return mapper.toReadDto(repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id)));
	}

	@Override
	public List<DietsReadDto> findAll() {
		return mapper.toReadDtos(repository.findAll());
	}

	@Override
	public void saveDto(DietsCreateDto dto) {repository.save(mapper.toEntity(dto));}


}
