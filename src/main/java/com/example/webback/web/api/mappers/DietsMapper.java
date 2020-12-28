package com.example.webback.web.api.mappers;

import com.example.webback.business.entity.DietsEntity;
import com.example.webback.web.api.dto.create.DietsCreateDto;
import com.example.webback.web.api.dto.read.DietsReadDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public abstract class DietsMapper {

    public abstract DietsReadDto toReadDto (DietsEntity entity);

    public abstract List<DietsReadDto> toReadDtos (List<DietsEntity> entities);

    public abstract DietsEntity toEntity (DietsCreateDto dto);

}
